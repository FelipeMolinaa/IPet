package com.proudpet.ipet.Activitys.Forms;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.proudpet.ipet.R;
import com.proudpet.ipet.adapters.PegaDataAdapter;
import com.proudpet.ipet.classes.Vacina;
import com.proudpet.ipet.classes.VacinaPronta;
import com.proudpet.ipet.database.VacinasDatabase;
import com.proudpet.ipet.database.dao.VacinasDAO;
import java.util.Calendar;

public class activityFormularioVacinas extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private String[] ArrayTipoVacina = new String[]{"Vacina","Vermifogo"};
    private static boolean gambiarra = true;
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Nova Vacina";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita dados de: ";
    private TextView campoNomeVacina;
    private EditText campoDataVacina;
    private TextView campoDataValidade;

    private VacinaPronta vacinaPronta;
    private Vacina vacina;
    private VacinasDAO dao;
    Calendar calendar = Calendar.getInstance();
    private String TagCalendario;
    private Context context;
    private int idAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_vacinas);
        VacinasDatabase database = VacinasDatabase.getInstance(this);
        dao = database.getRoomVacinaDAO();
        inicializacaoDosCampos();
        carregaVacina();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
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
            if(campoDataVacina.length() == 0){
                Toast.makeText(this, "Coloque a Data em que seu animal foi vacinado", Toast.LENGTH_SHORT).show();
            }else{
                finalizarFormulario();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void finalizarFormulario() {
        preencherVacina();
        if (vacina.temIdValido()){
            dao.edita(vacina);
        }else{
            Toast.makeText(this, vacina.getNome() + "foi adicionado com sucesso", Toast.LENGTH_SHORT).show();
            dao.salva(vacina);
        }
        finish();
    }

    private void preencherVacina(){
        String nome = campoNomeVacina.getText().toString();
        String dataVacinacao = campoDataVacina.getText().toString();
        String dataValidade = campoDataValidade.getText().toString();

        Toast.makeText(this, nome, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, dataVacinacao, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, dataValidade, Toast.LENGTH_SHORT).show();
        vacina.setNome(nome);
        vacina.setDataVacina(dataVacinacao);
        vacina.setDataValidade(dataValidade);
        vacina.setObrigatorio(true);

        if(idAnimal != 0){
            vacina.setIdAnimal(idAnimal);
        }
    }

    private void carregaVacina() {
        Intent dados = getIntent();
        if(dados.hasExtra("Vacina")){
            vacina = (Vacina) dados.getSerializableExtra("Vacina");
            setTitle(TITULO_APPBAR_EDITA_ALUNO + vacina.getNome());
            preencherCampos();
        }else if(dados.hasExtra("VacinaEscolhida")){
            vacinaPronta = (VacinaPronta) dados.getSerializableExtra("VacinaEscolhida");
            idAnimal = (int) dados.getSerializableExtra("idAnimal");
            preencherCamposVacinaPronta();
            vacina = new Vacina();
        }
        else{
            idAnimal = dados.getIntExtra("Animal", 0);
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            vacina = new Vacina();
        }
    }

    private void preencherCamposVacinaPronta() {
        campoNomeVacina.setText(vacinaPronta.getNome());
        campoDataValidade.setText(vacinaPronta.getStringValidade());
    }

    private void preencherCampos() {
        campoNomeVacina.setText(vacina.getNome());
        campoDataVacina.setText(vacina.getDataVacina());
        campoDataValidade.setText(vacina.getDataValidade());
    }

    private void inicializacaoDosCampos() {
        campoNomeVacina = findViewById(R.id.TextNomeDaVacina);
        campoDataVacina = findViewById(R.id.BotaoAbrirCalendarioDataDeVacinacao);
        campoDataValidade = findViewById(R.id.TextDataValidadeVacina);
        configuraCalendarios();
    }

    private void configuraCalendarios() {
        campoDataVacina.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(gambiarra){
                    DialogFragment pegaData = new PegaDataAdapter();
                    pegaData.show(getSupportFragmentManager(), "calendarioDataVacina");
                    TagCalendario = "calendarioDataVacina";
                    gambiarra = false;
                }

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
        String textoValidade = (dayOfMonth+"/"+(month + 1)+"/"+(year + 1) );
        if(TagCalendario == "calendarioDataVacina"){
            campoDataVacina.setText(textoData);
            campoDataValidade.setText(textoValidade);
        }
        campoDataValidade.requestFocus();
        gambiarra = true;
    }
}
