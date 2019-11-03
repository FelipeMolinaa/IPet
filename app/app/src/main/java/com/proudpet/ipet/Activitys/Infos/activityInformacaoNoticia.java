package com.proudpet.ipet.Activitys.Infos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.proudpet.ipet.R;
import com.proudpet.ipet.classes.Noticia;

public class activityInformacaoNoticia extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao_noticia);
        Intent intent = getIntent();
        if(intent.hasExtra("Noticia")){
            Noticia noticia = (Noticia) intent.getSerializableExtra("Noticia");
            myWebView = (WebView) findViewById(R.id.webView);
            myWebView.loadUrl(noticia.getLink() );

            setTitle(noticia.getFonte());

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }else if(intent.hasExtra("Site")){
            myWebView = (WebView) findViewById(R.id.webView);
            myWebView.loadUrl((String) intent.getSerializableExtra("Site"));

            setTitle("Sabicão");

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }



    }
}
