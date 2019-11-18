package com.proudpet.ipet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.VacinaPronta;

import java.util.ArrayList;
import java.util.List;

public class ListaVacinasProntasAdapter extends BaseAdapter {

    private final Context context;
    private final List<VacinaPronta> vacinasProntas = new ArrayList<>();

    public ListaVacinasProntasAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return vacinasProntas.size();
    }

    @Override
    public Object getItem(int i) {
        return vacinasProntas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return vacinasProntas.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        VacinaPronta noticiaDevolvida = vacinasProntas.get(position);
        vincula(viewCriada, noticiaDevolvida);

        return viewCriada;
    }

    private void vincula(View viewCriada, VacinaPronta vacinaDevolvida) {
        TextView campoNomeVacina = viewCriada.findViewById(R.id.ItemVacinaDadosTextNomeDaVacina);
        campoNomeVacina.setText(vacinaDevolvida.getNome());
        TextView campoValidadeVacina = viewCriada.findViewById(R.id.ItemVacinaDadosTextValidadeVacina);
        campoValidadeVacina.setText(vacinaDevolvida.getStringValidade());

        ImageView campoFotoAnimal = viewCriada.findViewById(R.id.ItemVacindaDadosImgAnimalVacina);

        if(vacinaDevolvida.getAnimal() == 2){
             campoFotoAnimal.setImageResource(R.mipmap.img_gato_emoji_foreground);
        }
        if(vacinaDevolvida.getAnimal() == 1){
            campoFotoAnimal.setImageResource(R.mipmap.img_cachorro_emoji_foreground);
        }
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_vacina_dados, parent, false);
    }

    public void adicionaVacina(VacinaPronta vacina){
        vacinasProntas.add(vacina);
    }
}