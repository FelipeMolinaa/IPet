package com.proudpet.ipet.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.proudpet.ipet.adapters.ListaAnimaisAdapter;
import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.database.AnimaisDatabase;
import com.proudpet.ipet.database.dao.AnimaisDAO;

public class ListaAnimaisView {

    private final ListaAnimaisAdapter adapter;
    private final AnimaisDAO dao;
    private final Context context;

    public ListaAnimaisView(Context context) {
        this.context = context;
        this.adapter = new ListaAnimaisAdapter(this.context);
        this.dao = AnimaisDatabase.getInstance(context)
                .getRoomAnimalDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("removendo Animal")
                .setMessage("tem certeza que quer remover o animal?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Animal animal = (Animal) adapter.getItem(menuInfo.position);
                    remove(animal);
                })
                .setNegativeButton("não", null)
                .show();
    }

    private void remove(Animal animalEscolhido) {
        dao.remove(animalEscolhido);
        adapter.remove(animalEscolhido);
    }

    public void configuraAdapter(ListView listaAnimais) {
        listaAnimais.setAdapter(adapter);
    }

    public void atualizaAnimais() {
        adapter.atualiza(dao.todos());
    }

    public Animal confirmaEdicao(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Animal animal = (Animal) adapter.getItem(menuInfo.position);
        return animal;
    }
}