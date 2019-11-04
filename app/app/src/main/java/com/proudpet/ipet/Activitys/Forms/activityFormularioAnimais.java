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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.proudpet.ipet.R;
import com.proudpet.ipet.adapters.PegaDataAdapter;
import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.database.AnimaisDatabase;
import com.proudpet.ipet.database.VacinasDatabase;
import com.proudpet.ipet.database.dao.AnimaisDAO;
import com.proudpet.ipet.database.dao.VacinasDAO;

import java.util.Calendar;

public class activityFormularioAnimais extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String[] ArrayEspecie = new String[]{"Escolha a especie","Cachorro", "Gato"};
    private String[] ArraySexo = new String[]{"Escolha o genero","Femea", "Macho"};

    private static boolean Gambiarra = true;
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Animal";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita dados de: ";
    private EditText campoNome;
    private EditText campoDataNascimento;
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
        campoNome.requestFocus();
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
            analizaCampos();
        }
        return super.onOptionsItemSelected(item);
    }

    private void analizaCampos() {
        String campoEspecieString = campoEspecie.getSelectedItem().toString();
        String campoGeneroString = campoSexo.getSelectedItem().toString();

        if (campoNome.length() == 0){
            Toast.makeText(this, "Seu animal Precisa de um nome", Toast.LENGTH_SHORT).show();
        }
        else if (campoDataNascimento.length() > 15){
            Toast.makeText(this, "Seu animal tem um nome muito grande", Toast.LENGTH_SHORT).show();
        }
        else if (campoDataNascimento.length() == 0){
            Toast.makeText(this, "Seu animal Precisa de uma data de nascimento", Toast.LENGTH_SHORT).show();
        }
        else if (campoPeso.length() == 0){
            Toast.makeText(this, "Seu animal Precisa de um peso", Toast.LENGTH_SHORT).show();
        }
        else if (campoPeso.length() > 199){
            Toast.makeText(this, "Seu animal Precisa de um peso", Toast.LENGTH_SHORT).show();
        }
        else if (campoEspecieString.equals("Escolha a especie")){
            Toast.makeText(this, "Escolha a especie do seu animal", Toast.LENGTH_SHORT).show();
        }
        else if (campoGeneroString.equals("Escolha o genero")){
            Toast.makeText(this, "Escolha um genero para seu animal", Toast.LENGTH_SHORT).show();
        }else{
            finalizarFormulario();
        }
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
        int sexo = (int)campoSexo.getSelectedItemId();
        int especie = (int)campoEspecie.getSelectedItemId();
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
        campoSexo.setSelection(animal.getSexo());
        campoEspecie.setSelection(animal.getEspecie());
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
        campoDataNascimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (Gambiarra){
                    DialogFragment pegaData = new PegaDataAdapter();
                    pegaData.show(getSupportFragmentManager(), "calendarioDataVacina");
                    Gambiarra = false;
                }

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

        campoDataNascimento.setText(dayOfMonth+"/"+ (month + 1) +"/"+year);
        campoPeso.requestFocus();
        Gambiarra = true;
    }

}
