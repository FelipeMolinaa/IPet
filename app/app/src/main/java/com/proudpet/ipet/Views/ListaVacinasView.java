package com.proudpet.ipet.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.proudpet.ipet.adapters.ListaVacinasAdapter;
import com.proudpet.ipet.classes.Vacina;
import com.proudpet.ipet.database.VacinasDatabase;
import com.proudpet.ipet.database.dao.VacinasDAO;

public class ListaVacinasView {

    private final ListaVacinasAdapter adapter;
    private final VacinasDAO dao;
    private final Context context;

    public ListaVacinasView(Context context) {
        this.context = context;
        this.adapter = new ListaVacinasAdapter(this.context);
        this.dao = VacinasDatabase.getInstance(context)
                .getRoomVacinaDAO();
    }


    public void configuraAdapter(ListView listaDeVacinas) {
        listaDeVacinas.setAdapter(adapter);
    }

    public void confirmaRemocao(MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("removendo Vacina")
                .setMessage("tem certeza que quer remover a vacina?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Vacina vacina = (Vacina) adapter.getItem(menuInfo.position);
                    remove(vacina);
                })
                .setNegativeButton("não", null)
                .show();
    }

    private void remove(Vacina vacina) {
        dao.remove(vacina);
        adapter.remove(vacina);
    }

    public void renova(Vacina vacina) {
        vacina.renovaVacina();
        dao.edita(vacina);
        adapter.atualiza(dao.todos(vacina.getIdAnimal()));
    }

    public Vacina confirmaEdicao(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Vacina vacina = (Vacina) adapter.getItem(menuInfo.position);
        return vacina;
    }

    public void atualizaVacinas(int id) {
        adapter.atualiza(dao.todos(id));
    }

    public void confirmaRenovacao(MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Renovando Vacina")
                .setMessage("Gostaria de renovar esta vacina por mais um ano?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Vacina vacina = (Vacina) adapter.getItem(menuInfo.position);
                    renova(vacina);
                })
                .setNegativeButton("não", null)
                .show();
    }
}
