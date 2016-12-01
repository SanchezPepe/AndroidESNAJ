package com.esnaj.androidesnaj;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abreESNAJ(View v){
        abreURL("http://www.esnaj.com/");

    }

    public void abreURL(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void registrar(View v){
        Intent intent = new Intent(MainActivity.this, Registro.class);
        startActivity(intent);
    }
}
