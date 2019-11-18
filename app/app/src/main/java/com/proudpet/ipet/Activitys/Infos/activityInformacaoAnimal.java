package com.proudpet.ipet.Activitys.Infos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.proudpet.ipet.Activitys.Forms.activityFormularioAnimais;
import com.proudpet.ipet.Activitys.Listas.activityListaVacinasAnimal;
import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.database.AnimaisDatabase;
import com.proudpet.ipet.database.dao.AnimaisDAO;

public class activityInformacaoAnimal extends AppCompatActivity {

    Animal animal;
    AnimaisDAO dao;

    TextView nomeAnimal;
    TextView dataNascimento;
    TextView generoAnimal;
    TextView pesoAnimal;
    ImageView imagemAnimal;
    ImageView Castrado;
    Button botaoVacinas;
    Button botaoEditar;
    Button botaoRemover;
    AlertDialog alertaExclusao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao_animal);
        PegaDados();
        PegaViews();
        ConfiguraBotoes();
    }

    private void ConfiguraBotoes() {
        botaoVacinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiPraInformacoes = new Intent(activityInformacaoAnimal.this, activityListaVacinasAnimal.class);
                vaiPraInformacoes.putExtra("Animal", animal);
                startActivity(vaiPraInformacoes);
            }
        });

        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiProFormEdita = new Intent(activityInformacaoAnimal.this, activityFormularioAnimais.class);
                vaiProFormEdita.putExtra("Animal", animal);
                startActivity(vaiProFormEdita);
            }
        });

        botaoRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activityInformacaoAnimal.this);
                builder.setTitle("Remover");
                builder.setMessage("Tem certeza que deseja remover este Animal?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        dao.remove(animal);
                        finish();
                    }
                });
                builder.setNegativeButton("Não", null);

                alertaExclusao = builder.create();
                alertaExclusao.show();
            }
        });
    }

    private void PegaViews() {
        nomeAnimal = findViewById(R.id.TXTNomeAnimal);
        dataNascimento = findViewById(R.id.TXTDataDeNascimento);
        generoAnimal = findViewById(R.id.TXTGenero);
        pesoAnimal = findViewById(R.id.TXTPesoAnimal);
        imagemAnimal = findViewById(R.id.IMGAnimal);
        Castrado = findViewById(R.id.SwitchCastrado);
        botaoVacinas = findViewById(R.id.BotaoVerVacinas);
        botaoEditar = findViewById(R.id.BotaoEditar);
        botaoRemover = findViewById(R.id.BotaoRemover);
    }

    private void PegaDados() {
        Intent dados = getIntent();
        animal = (Animal) dados.getSerializableExtra("Animal");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConfiguraBD();
        PreencheView();
    }

    private void PreencheView() {
        nomeAnimal.setText(animal.getNome());
        dataNascimento.setText(animal.getDataNascimento());
        generoAnimal.setText(animal.getStringSexo());
        pesoAnimal.setText(animal.getPesoString());
        setTitle("Informações de "+ animal.getNome());
        ConfiguraImagemAnimal();
        ConfiguraImagemCastrado();
    }

    private void ConfiguraImagemCastrado() {
        if(animal.isCastrado()){
            Castrado.setImageResource(R.drawable.icone_vacina_verde);
        }else{
            Castrado.setImageResource(R.drawable.icone_vacina_vermelho);
        }
    }

    private void ConfiguraImagemAnimal() {
        if(animal.getEspecie() == 2){
            imagemAnimal.setImageResource(R.mipmap.img_gato_emoji_foreground);
        }
        if(animal.getEspecie() == 1){
            imagemAnimal.setImageResource(R.mipmap.img_cachorro_emoji_foreground);
        }
    }

    private void ConfiguraBD() {
        AnimaisDatabase database = AnimaisDatabase.getInstance(this);
        dao = database.getRoomAnimalDAO();
        animal = dao.pegaAnimal(animal.getId());
    }
}