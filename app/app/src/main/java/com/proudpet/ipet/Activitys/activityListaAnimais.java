package com.proudpet.ipet.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.classes.ListaAnimaisView;

public class activityListaAnimais extends AppCompatActivity {

    private ListaAnimaisView listaAnimaisView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_animais);
        setTitle("Meus Pets");
        listaAnimaisView = new ListaAnimaisView(this);
        configuraFAB();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.lista_animais_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.itemMenuRemover){
            listaAnimaisView.confirmaRemocao(item);
        }
        else if(itemId == R.id.itemMenuEditar){
            Animal animalSelecionado = listaAnimaisView.confirmaEdicao(item);
            abreFormEditaAnimal(animalSelecionado);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        ListView listaAnimais = findViewById(R.id.listaDeAnimais);
        listaAnimaisView.configuraAdapter(listaAnimais);
        configuraListenerDeCliquePorItem(listaAnimais);
        registerForContextMenu(listaAnimais);
    }

    private void configuraListenerDeCliquePorItem(ListView listaAnimais) {
        listaAnimais.setOnItemClickListener((adapterView, view, posicao, id) ->{
            Animal animalEscolhido = (Animal) adapterView.getItemAtPosition(posicao);
            Intent vaiPraListaDeVacinas = new Intent(activityListaAnimais.this, activityListaVacinas.class);
            vaiPraListaDeVacinas.putExtra("Animal", animalEscolhido);
            startActivity(vaiPraListaDeVacinas);
        });
    }

    private void abreFormEditaAnimal(Animal animalEscolhido) {
        Intent vaiProFormEdita = new Intent(activityListaAnimais.this, activityFormularioAnimais.class);
        vaiProFormEdita.putExtra("Animal", animalEscolhido);
        startActivity(vaiProFormEdita);
    }

    private void configuraFAB() {
        FloatingActionButton FabID = findViewById(R.id.FabAdicionaNovoAnimal);
        FabID.setOnClickListener(v -> AbreFormNovoAnimal());
    }

    private void AbreFormNovoAnimal(){
        startActivity(new Intent(activityListaAnimais.this, activityFormularioAnimais.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAnimaisView.atualizaAnimais();
    }
}
