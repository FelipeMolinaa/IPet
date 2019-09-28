package com.proudpet.ipet.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.proudpet.ipet.Activitys.Listas.activityListaAnimais;
import com.proudpet.ipet.R;

public class activityMenuPrincipal extends AppCompatActivity {

    Button botaoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        pegaIdBotoesMenu();
        configuraOnClick();
    }

    private void configuraOnClick() {
        botaoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activityMenuPrincipal.this, activityListaAnimais.class));
            }
        });
    }

    private void pegaIdBotoesMenu() {
        botaoMenu = findViewById(R.id.menuBotaoAbrirListaMeusAnimais);

    }


}
