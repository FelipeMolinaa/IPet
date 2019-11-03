package com.proudpet.ipet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.classes.Noticia;

import java.util.ArrayList;
import java.util.List;

public class ListaNoticiasAdapter extends BaseAdapter {

    private final Context context;
    private final List<Noticia> noticias = new ArrayList<>();

    public ListaNoticiasAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int position) {
        return noticias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return noticias.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Noticia noticiaDevolvida = noticias.get(position);
        vincula(viewCriada, noticiaDevolvida);

        return viewCriada;
    }

    private void vincula(View viewCriada, Noticia noticiaDevolvida) {
        TextView dia = viewCriada.findViewById(R.id.itemDiaListaNoticias);
        dia.setText(noticiaDevolvida.getDia());
        TextView mes = viewCriada.findViewById(R.id.itemMesListaNoticias);
        mes.setText(noticiaDevolvida.getMes());
        TextView ano = viewCriada.findViewById(R.id.itemAnoListaNoticias);
        ano.setText(noticiaDevolvida.getAno());
        TextView titulo = viewCriada.findViewById(R.id.itemTituloListaNoticias);
        titulo.setText(noticiaDevolvida.getTitulo());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_noticia, parent, false);
    }

    public void adicionaNoticia(Noticia noticia){
        noticias.add(noticia);
    }
}
