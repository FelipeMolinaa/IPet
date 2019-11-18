package com.proudpet.ipet.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.proudpet.ipet.Activitys.Listas.activityListaAnimais;
import com.proudpet.ipet.Activitys.Listas.activityListaNoticias;
import com.proudpet.ipet.R;

public class activityMenuPrincipal extends AppCompatActivity {

    Button botaoMenu;
    Button botaoNoticias;
    ImageButton botaoPatrocinio;

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

        botaoPatrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://sabicao.com.br"));
                startActivity(browserIntent);
            }
        });

        botaoNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activityMenuPrincipal.this, activityListaNoticias.class));
            }
        });
    }

    private void pegaIdBotoesMenu() {
        botaoMenu = findViewById(R.id.menuBotaoAbrirListaMeusAnimais);
        botaoNoticias = findViewById(R.id.menuBotaoAbrirNoticias);
        botaoPatrocinio = findViewById(R.id.menuBotaoAbrirSabicao);
    }
}