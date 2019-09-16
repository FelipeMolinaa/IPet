package com.proudpet.ipet.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.classes.ListaVacinasView;
import com.proudpet.ipet.classes.Vacina;

public class activityListaVacinas extends AppCompatActivity {

    Animal animal;
    private ListaVacinasView listaVacinasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vacinas);
        listaVacinasView = new ListaVacinasView(this);
        iniciaActivity();
        configuraFAB();
        configuraLista();
    }

    private void ConfiguraInfoAnimal() {
        setTitle("vacinas de: " + animal.getNome());

        TextView TXTEspecie = findViewById(R.id.ListaVacinasInfoEspecie);
        TXTEspecie.setText(animal.getEspecie());

        TextView TXTSexo = findViewById(R.id.ListaVacinasInfoSexo);
        TXTSexo.setText(animal.getSexo());

        TextView TXTCastrado = findViewById(R.id.ListaVacinasInfoCastrado);
        TXTCastrado.setText(animal.getStringCastrado());

        TextView TXTPeso = findViewById(R.id.ListaVacinasInfoPeso);
        TXTPeso.setText(animal.getStringPeso());

        TextView TXTDataNascimento = findViewById(R.id.ListaVacinasInfoDataNascimento);
        TXTDataNascimento.setText(animal.getDataNascimento());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.lista_vacinas_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.itemMenuRemover){
            listaVacinasView.confirmaRemocao(item);
        }
        else if(itemId == R.id.itemMenuEditar){
            Vacina vacinaSelecionada = listaVacinasView.confirmaEdicao(item);
            abreFormEditaAnimal(vacinaSelecionada);
        }
        else if(itemId == R.id.itemMenurenovar){
            listaVacinasView.confirmaRenovacao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void abreFormEditaAnimal(Vacina vacinaSelecionada) {
        Intent vaiProFormEdita = new Intent(activityListaVacinas.this, activityFormularioVacinas.class);
        vaiProFormEdita.putExtra("Vacina", vacinaSelecionada);
        startActivity(vaiProFormEdita);
    }

    private void configuraLista() {
        ListView listaDeVacinas = findViewById(R.id.ListaVacinasListView);
        listaVacinasView.configuraAdapter(listaDeVacinas);
        configuraListenerDeClickPorItem(listaDeVacinas);
        registerForContextMenu(listaDeVacinas);
    }

    //adicionar uma caixa de dialogo perguntando se quer renovar
    private void configuraListenerDeClickPorItem(ListView listaDeVacinas) {
        listaDeVacinas.setOnItemClickListener((adapterView, view, position, id) ->{
            Vacina vacinaEscolhida = (Vacina) adapterView.getItemAtPosition(position);
            Intent vaiPraListaDeVacinas = new Intent(activityListaVacinas.this, activityFormularioVacinas.class);
            vaiPraListaDeVacinas.putExtra("Vacina", vacinaEscolhida);
            startActivity(vaiPraListaDeVacinas);
        });

    }

    private void configuraFAB() {
        FloatingActionButton FAB = findViewById(R.id.FabAdicionaNovaVacina);
        FAB.setOnClickListener(v -> abreFormNovaVacina());
    }

    private void iniciaActivity() {
        Intent dados = getIntent();
        animal = (Animal) dados.getSerializableExtra("Animal");
        ConfiguraInfoAnimal();
    }

    private void abreFormNovaVacina(){
        Intent vaiPraListaDeVacinas = new Intent(activityListaVacinas.this, activityFormularioVacinas.class);
        vaiPraListaDeVacinas.putExtra("Animal", animal.getId());
        startActivity(vaiPraListaDeVacinas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaVacinasView.atualizaVacinas(animal.getId());
    }
}
