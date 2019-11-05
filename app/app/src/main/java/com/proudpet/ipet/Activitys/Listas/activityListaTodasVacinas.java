package com.proudpet.ipet.Activitys.Listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.proudpet.ipet.Activitys.Forms.activityFormularioVacinas;
import com.proudpet.ipet.Activitys.Infos.activityInformacaoNoticia;
import com.proudpet.ipet.R;
import com.proudpet.ipet.Views.ListaNoticiasView;
import com.proudpet.ipet.Views.ListaVacinasProntasView;
import com.proudpet.ipet.classes.Noticia;
import com.proudpet.ipet.classes.VacinaPronta;

public class activityListaTodasVacinas extends AppCompatActivity {

    private ListaVacinasProntasView listaVacinasProntasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_todas_vacinas);
        setTitle("Vacina");
        listaVacinasProntasView = new ListaVacinasProntasView(this);
        configuraLista();
    }

    private void configuraLista() {
        ListView listaVacinasProntas = findViewById(R.id.ListaVacinasProntasListView);
        listaVacinasProntasView.configuraAdapter(listaVacinasProntas);
        configuraListenerDeCliquePorItem(listaVacinasProntas);
        registerForContextMenu(listaVacinasProntas);
    }

    private void configuraListenerDeCliquePorItem(ListView listaVacinasProntas) {
        listaVacinasProntas.setOnItemClickListener((adapterView, view, position, id) ->{
            VacinaPronta VacinaProntaEscolhida = (VacinaPronta) adapterView.getItemAtPosition(position);
            Intent vaiPraVacinaPronta = new Intent(activityListaTodasVacinas.this, activityFormularioVacinas.class);
            vaiPraVacinaPronta.putExtra("VacinaEscolhida", VacinaProntaEscolhida);
            Intent dados = getIntent();
            vaiPraVacinaPronta.putExtra("idAnimal", dados.getSerializableExtra("Animal"));
            startActivity(vaiPraVacinaPronta);
        });
    }
}
