package com.proudpet.ipet.Activitys.Forms;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.adapters.PegaDataAdapter;
import com.proudpet.ipet.classes.Vacina;
import com.proudpet.ipet.database.AnimaisDatabase;
import com.proudpet.ipet.database.VacinasDatabase;
import com.proudpet.ipet.database.dao.AnimaisDAO;
import com.proudpet.ipet.database.dao.VacinasDAO;

import java.text.DateFormat;
import java.util.Calendar;

public class activityFormularioAnimais extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String[] ArrayEspecie = new String[]{"Cachorro", "Gato"};
    private String[] ArraySexo = new String[]{"Femea", "Macho"};

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Animal";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita dados de: ";
    private EditText campoNome;
    private Button campoDataNascimento;
    private EditText campoPeso;
    private Spinner campoSexo;
    private Spinner campoEspecie;
    private Switch campoCastrado;
    private AnimaisDAO daoAnimais;
    private VacinasDAO daoVacinas;
    private Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_animais);
        AnimaisDatabase databaseAnimais = AnimaisDatabase.getInstance(this);
        daoAnimais = databaseAnimais.getRoomAnimalDAO();
        VacinasDatabase databaseVacinas = VacinasDatabase.getInstance(this);
        daoVacinas = databaseVacinas.getRoomVacinaDAO();
        inicializacaoDosCampos();
        carregaAnimal();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.formulario_add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.IconeSalvarFormularioAddAnimais){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void finalizarFormulario() {
        preencherAnimal();
        if (animal.temIdValido()){
            daoAnimais.edita(animal);
        }else{
            Toast.makeText(this,animal.getNome() + " foi adicionado com sucesso", Toast.LENGTH_SHORT).show();
            daoAnimais.salva(animal);
        }
        finish();
    }

    private void preencherAnimal() {
        String nome = campoNome.getText().toString();
        String dataNascimento = campoDataNascimento.getText().toString();
        String peso = campoPeso.getText().toString();
        String sexo = campoSexo.getSelectedItem().toString();
        String especie = campoEspecie.getSelectedItem().toString();
        boolean castrado = campoCastrado.isChecked();

        animal.setNome(nome);
        animal.setDataNascimento(dataNascimento);
        animal.setPeso(peso);
        animal.setSexo(sexo);
        animal.setEspecie(especie);
        animal.setCastrado(castrado);
    }

    private void carregaAnimal() {
        Intent dados = getIntent();
        if (dados.hasExtra("Animal")){
            animal = (Animal) dados.getSerializableExtra("Animal");
            setTitle(TITULO_APPBAR_EDITA_ALUNO + animal.getNome());
            preencherCampos();
        }else{
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            animal = new Animal();
        }
    }

    private void preencherCampos() {
        campoNome.setText(animal.getNome());
        campoDataNascimento.setText(animal.getDataNascimento());
        campoPeso.setText(animal.getPeso());
        campoCastrado.setChecked(animal.isCastrado());
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.FormularioAnimalTxtBoxNomeAnimal);
        campoDataNascimento = findViewById(R.id.FormularioAnimalTxtBoxDataNascimentoAnimal);
        campoPeso = findViewById(R.id.FormularioAnimalTxtBoxPesoAnimal);
        campoSexo = findViewById(R.id.FormularioAnimalSpinnerSexoAnimal);
        campoEspecie = findViewById(R.id.FormularioAnimalSpinnerEspecieAnimal);
        campoCastrado = findViewById(R.id.FormularioAnimalSwitchCastrado);

        configuraSpinnerSexo();
        configuraSpinnerEspecie();
        configuraCalendario();
    }

    private void configuraCalendario() {
        campoDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment pegaData = new PegaDataAdapter();
                pegaData.show(getSupportFragmentManager(), "calendarioDataVacina");
            }
        });
    }

    private void configuraSpinnerEspecie() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ArrayEspecie);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoEspecie.setAdapter(adapter);
    }

    private void configuraSpinnerSexo() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ArraySexo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoSexo.setAdapter(adapter);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        campoDataNascimento.setText(dayOfMonth+"/"+ (month + 1) +"/"+year);
    }
}
