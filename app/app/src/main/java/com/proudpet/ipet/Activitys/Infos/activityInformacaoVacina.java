package com.proudpet.ipet.Activitys.Infos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.proudpet.ipet.Activitys.Forms.activityFormularioVacinas;
import com.proudpet.ipet.Activitys.Listas.activityListaVacinas;
import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Vacina;
import com.proudpet.ipet.database.VacinasDatabase;
import com.proudpet.ipet.database.dao.VacinasDAO;

public class activityInformacaoVacina extends AppCompatActivity {

    Vacina vacina;
    Vacina vacinaBD;
    VacinasDAO dao;
    Context context;

    TextView nomeVacina;
    TextView mensagemValidade;
    TextView dataValidade;
    ImageView imagemVacinaStatus;
    Switch interruptorObrigatorio;
    Button botaoEditar;
    Button botaoRemover;
    Button botaoRenovar;

    AlertDialog alertaExclusao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao_vacina);

        PegaDados();
        PegaViews();
        PreencheView();
        ConfiguraBotoes();
    }

    private void ConfiguraBotoes() {
        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiProFormEdita = new Intent(activityInformacaoVacina.this, activityFormularioVacinas.class);
                vaiProFormEdita.putExtra("Vacina", vacina);
                startActivity(vaiProFormEdita);
            }
        });

        botaoRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vacina.isObrigatorio()){
                    Toast.makeText(context, "Esta Vacina é obrigatoria, portanto não pode ser removida", Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activityInformacaoVacina.this);
                    builder.setTitle("Remover");
                    builder.setMessage("tem certeza que deseja remover esta vacina?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            dao.remove(vacina);
                            finish();
                        }
                    });
                    builder.setNegativeButton("Não", null);

                    alertaExclusao = builder.create();
                    alertaExclusao.show();
                }

            }
        });
    }

    private void PreencheView() {
        nomeVacina.setText(vacina.getNome());
        mensagemValidade.setText(vacina.getValidadeString());
        dataValidade.setText(vacina.getDataValidade());
        interruptorObrigatorio.setChecked(vacina.isObrigatorio());
        configuraImagemStatus();
    }

    private void configuraImagemStatus() {
        int validade = Integer.parseInt(vacina.getValidade());
        if(validade <= 0){
            imagemVacinaStatus.setImageResource(R.drawable.icone_vacina_vermelho);
        }
        else if(validade <= 100){
            imagemVacinaStatus.setImageResource(R.drawable.icone_vacina_amarelo);
        }
        else {
            imagemVacinaStatus.setImageResource(R.drawable.icone_vacina_verde);
        }
    }

    private void PegaViews() {
        nomeVacina = findViewById(R.id.TXTNomeVacina);
        mensagemValidade = findViewById(R.id.TXTMensagemValidade);
        dataValidade = findViewById(R.id.TXTDataValidade);
        imagemVacinaStatus = findViewById(R.id.IMGValidadeStatus);
        interruptorObrigatorio = findViewById(R.id.InterruptorObrigatorio);
        botaoEditar = findViewById(R.id.BotaoEditar);
        botaoRemover = findViewById(R.id.BotaoRemover);
        botaoRenovar = findViewById(R.id.BotaoRenovar);
    }

    private void PegaDados() {
        Intent dados = getIntent();
        vacina = (Vacina) dados.getSerializableExtra("Vacina");

    }

    private void ConfiguraBD(){
        VacinasDatabase database = VacinasDatabase.getInstance(this);
        dao = database.getRoomVacinaDAO();
        vacina = dao.pegaVacina(vacina.getId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConfiguraBD();
        PreencheView();
    }
}
