package com.proudpet.ipet.Activitys.Infos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Vacina;

public class activityInformacaoVacina extends AppCompatActivity {

    Vacina vacina;
    TextView nomeVacina;
    TextView mensagemValidade;
    TextView dataValidade;
    ImageView imagemVacinaStatus;
    Switch interruptorObrigatorio;
    Button botaoEditar;
    Button botaoRemover;
    Button botaoRenovar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao_vacina);

        PegaDados();
        PegaViews();
        PreencheView();
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
    }

    private void PegaDados() {
        Intent dados = getIntent();
        vacina = (Vacina) dados.getSerializableExtra("Vacina");
    }
}
