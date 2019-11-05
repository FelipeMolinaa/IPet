package com.proudpet.ipet.Views;

import android.content.Context;
import android.widget.ListView;

import com.proudpet.ipet.adapters.ListaVacinasProntasAdapter;
import com.proudpet.ipet.classes.VacinaPronta;

public class ListaVacinasProntasView {
    private final ListaVacinasProntasAdapter adapter;
    private final Context context;

    public ListaVacinasProntasView(Context context) {
        this.context = context;
        this.adapter = new ListaVacinasProntasAdapter(this.context);
        AdicionaVacinasProntas();
    }

    private void AdicionaVacinasProntas() {
       adapter.adicionaVacina(new VacinaPronta("V8", 1, 1));
       adapter.adicionaVacina(new VacinaPronta("V10", 1, 1));
       adapter.adicionaVacina(new VacinaPronta("AntRabica", 1, 1));
       adapter.adicionaVacina(new VacinaPronta("AntRabica", 1, 2));
    }

    public void configuraAdapter(ListView listaVacinasProntas){
        listaVacinasProntas.setAdapter(adapter);
    }

}
