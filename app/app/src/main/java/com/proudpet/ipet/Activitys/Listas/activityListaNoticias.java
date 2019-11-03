package com.proudpet.ipet.Activitys.Listas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.proudpet.ipet.Activitys.Infos.activityInformacaoNoticia;
import com.proudpet.ipet.R;
import com.proudpet.ipet.Views.ListaNoticiasView;
import com.proudpet.ipet.classes.Noticia;

public class activityListaNoticias extends AppCompatActivity {

    private ListaNoticiasView listaNoticiasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_noticias);
        setTitle("Noticias (Em desenvolvimento)");
        listaNoticiasView = new ListaNoticiasView(this);
        configuraLista();
    }

    private void configuraLista() {
        ListView listaNoticias = findViewById(R.id.ListaNoticias);
        listaNoticiasView.configuraAdapter(listaNoticias);
        configuraListenerDeCliquePorItem(listaNoticias);
        registerForContextMenu(listaNoticias);
    }

    private void configuraListenerDeCliquePorItem(ListView listaNoticias) {
        listaNoticias.setOnItemClickListener((adapterView, view, position, id) ->{
            Noticia noticiaEscolhida = (Noticia) adapterView.getItemAtPosition(position);
            Intent vaiPraNoticia = new Intent(activityListaNoticias.this, activityInformacaoNoticia.class);
            vaiPraNoticia.putExtra("Noticia", noticiaEscolhida);
            startActivity(vaiPraNoticia);
        });
    }


}
