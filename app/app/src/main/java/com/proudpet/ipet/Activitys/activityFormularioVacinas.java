package com.proudpet.ipet.Activitys;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.DatePickerFragment;
import com.proudpet.ipet.classes.Vacina;
import com.proudpet.ipet.database.VacinasDatabase;
import com.proudpet.ipet.database.dao.VacinasDAO;
import java.util.Calendar;

public class activityFormularioVacinas extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String[] ArrayTipoVacina = new String[]{"Vacina","Vermifogo"};

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Nova Vacina";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita dados de: ";
    private EditText campoNomeVacina;
    private Button campoDataVacina;
    private Spinner campoTipoVacina;
    private Button campoDataValidade;

    private Vacina vacina;
    private VacinasDAO dao;
    Calendar calendar = Calendar.getInstance();
    private String TagCalendario;
    private Context context;
    private int idAnimal;

    public activityFormularioVacinas() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_vacinas);
        VacinasDatabase database = VacinasDatabase.getInstance(this);
        dao = database.getRoomVacinaDAO();

        inicializacaoDosCampos();
        carregaVacina();
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
        preencherVacina();
        if (vacina.temIdValido()){
            dao.edita(vacina);
        }else{
            Toast.makeText(this, vacina.getNome() + " foi adicionado com sucesso", Toast.LENGTH_SHORT).show();
            dao.salva(vacina);
        }
        finish();
    }

    private void preencherVacina(){
        String nome = campoNomeVacina.getText().toString();
        String dataVacinacao = campoDataVacina.getText().toString();
        String tipoVacina = campoTipoVacina.getSelectedItem().toString();
        String dataValidade = campoDataValidade.getText().toString();

        vacina.setNome(nome);
        vacina.setDataVacina(dataVacinacao);
        vacina.setTipo(tipoVacina);
        vacina.setDataValidade(dataValidade);

        if(idAnimal != 0){
            vacina.setIdAnimal(idAnimal);
        }

        Log.i("IdAnimal", "" + idAnimal);
    }

    private void carregaVacina() {
        Intent dados = getIntent();
        if(dados.hasExtra("Vacina")){
            vacina = (Vacina) dados.getSerializableExtra("Vacina");
            setTitle(TITULO_APPBAR_EDITA_ALUNO + vacina.getNome());
            preencherCampos();
        }
        else{
            idAnimal = dados.getIntExtra("Animal", 0);
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            vacina = new Vacina();
        }
    }

    private void preencherCampos() {
        campoNomeVacina.setText(vacina.getNome());
        campoDataVacina.setText(vacina.getDataVacina());
        campoDataValidade.setText(vacina.getDataValidade());
    }

    private void inicializacaoDosCampos() {
        campoNomeVacina = findViewById(R.id.TxtBoxNomeDaVacina);
        campoDataVacina = findViewById(R.id.BotaoAbrirCalendarioDataDeVacinacao);
        campoTipoVacina = findViewById(R.id.SpinnerTipoDeVacina);
        campoDataValidade = findViewById(R.id.BotaoAbrirCalendarioDataDeValidade);
        configuraCalendarios();
        configuraSpinnerSexo();
    }

    private void configuraSpinnerSexo() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ArrayTipoVacina);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoTipoVacina.setAdapter(adapter);
    }

    private void configuraCalendarios() {
        campoDataVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment pegaData = new DatePickerFragment();
                pegaData.show(getSupportFragmentManager(), "calendarioDataVacina");
                TagCalendario = "calendarioDataVacina";
            }
        });

        campoDataValidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment pegaData = new DatePickerFragment();
                pegaData.show(getSupportFragmentManager(), "calendarioDataValidade");
                TagCalendario = "calendarioDataValidade";
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String textoData = (dayOfMonth+"/"+(month + 1)+"/"+year);

        if(TagCalendario == "calendarioDataVacina"){
            campoDataVacina.setText(textoData);
        }else if(TagCalendario == "calendarioDataValidade"){
            campoDataValidade.setText(textoData);
        }
    }
}
