package com.proudpet.ipet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Vacina;

import java.util.ArrayList;
import java.util.List;

public class ListaVacinasAdapter extends BaseAdapter {
    private final Context context;
    private final List<Vacina> vacinas = new ArrayList<>();

    public ListaVacinasAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return vacinas.size();
    }

    @Override
    public Object getItem(int position) {
        return vacinas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return vacinas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Vacina VacinaDevolvida = vacinas.get(position);
        vincula(viewCriada, VacinaDevolvida);
        return viewCriada;
    }

    private void vincula(View viewCriada, Vacina vacinaDevolvida) {
        TextView nome = viewCriada.findViewById(R.id.LabelNomeVacina);
        nome.setText(vacinaDevolvida.getNome());

        TextView diasRestantes = viewCriada.findViewById(R.id.LabelDiasRestantes);
        diasRestantes.setText(vacinaDevolvida.getValidadeString());

        ImageView tempoImg = viewCriada.findViewById(R.id.ImagemItemVacina);
        int validade = Integer.parseInt(vacinaDevolvida.getValidade());
        if(validade <= 0){
            tempoImg.setImageResource(R.drawable.icone_vacina_vermelho);
        }
        else if(validade <= 100){
            tempoImg.setImageResource(R.drawable.icone_vacina_amarelo);
        }
        else {
            tempoImg.setImageResource(R.drawable.icone_vacina_verde);
        }
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_vacina,parent,false);
    }

    public void remove(Vacina vacina) {
        vacinas.remove(vacina);
        notifyDataSetChanged();
    }

    public void atualiza(List<Vacina> todos) {
        this.vacinas.clear();
        this.vacinas.addAll(todos);
        notifyDataSetChanged();
    }
}