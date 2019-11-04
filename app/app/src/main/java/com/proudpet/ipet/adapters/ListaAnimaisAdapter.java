package com.proudpet.ipet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Animal;

import java.util.ArrayList;
import java.util.List;

public class ListaAnimaisAdapter extends BaseAdapter {
    private final Context context;
    private final List<Animal> animais = new ArrayList<>();

    public ListaAnimaisAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return animais.size();
    }

    @Override
    public Object getItem(int position) {
        return animais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return animais.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Animal animalDevolvido = animais.get(position);
        vincula(viewCriada, animalDevolvido);
        return viewCriada;
    }

    private void vincula(View viewCriada, Animal animalDevolvido) {
        TextView nome = viewCriada.findViewById(R.id.itemAnimalNome);
        nome.setText(animalDevolvido.getNome());

        TextView genero = viewCriada.findViewById(R.id.itemAnimalGenero);
        genero.setText(animalDevolvido.getStringSexo());

        ImageView especie = viewCriada.findViewById(R.id.IconeTipoAnimal);

        if(animalDevolvido.getEspecie() == 2){
            especie.setImageResource(R.mipmap.img_gato_emoji_foreground);
        }
        if(animalDevolvido.getEspecie() == 1){
            especie.setImageResource(R.mipmap.img_cachorro_emoji_foreground);
        }
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
    }

    public void remove(Animal animalEscolhido) {
        animais.remove(animalEscolhido);
        notifyDataSetChanged();
    }

    public void atualiza(List<Animal> todos) {
        this.animais.clear();
        this.animais.addAll(todos);
        notifyDataSetChanged();
    }
}
