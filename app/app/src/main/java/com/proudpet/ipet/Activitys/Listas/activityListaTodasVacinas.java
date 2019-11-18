package com.proudpet.ipet.Activitys.Listas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.proudpet.ipet.Activitys.Forms.activityFormularioVacinas;
import com.proudpet.ipet.R;
import com.proudpet.ipet.Views.ListaVacinasProntasView;
import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.classes.VacinaPronta;

public class activityListaTodasVacinas extends AppCompatActivity {

    private ListaVacinasProntasView listaVacinasProntasView;
    Intent dados;
    Animal animal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_todas_vacinas);
        setTitle("Escolha uma vacina");
        dados = getIntent();
        animal = (Animal) dados.getSerializableExtra("Animal");
        listaVacinasProntasView = new ListaVacinasProntasView(this);
        configuraLista();

    }

    private void configuraLista() {
        ListView listaVacinasProntas = findViewById(R.id.ListaVacinasProntasListView);
        listaVacinasProntasView.configuraAdapter(listaVacinasProntas);
        configuraListenerDeCliquePorItem(listaVacinasProntas);
        registerForContextMenu(listaVacinasProntas);
    }

    private void configuraListenerDeCliquePorItem(ListView listaVacinasProntas) {
        listaVacinasProntas.setOnItemClickListener((adapterView, view, position, id) ->{
            VacinaPronta VacinaProntaEscolhida = (VacinaPronta) adapterView.getItemAtPosition(position);
            if(VacinaProntaEscolhida.getAnimal() == animal.getEspecie()){
                Intent vaiPraVacinaPronta = new Intent(activityListaTodasVacinas.this, activityFormularioVacinas.class);
                vaiPraVacinaPronta.putExtra("VacinaEscolhida", VacinaProntaEscolhida);
                vaiPraVacinaPronta.putExtra("Animal", dados.getSerializableExtra("Animal"));
                startActivity(vaiPraVacinaPronta);
            }else{
                Toast.makeText(this, "Esta Vacina não foi feita para esta especie de animal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}