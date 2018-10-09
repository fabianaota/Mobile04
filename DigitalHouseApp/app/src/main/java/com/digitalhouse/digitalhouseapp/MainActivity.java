package com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView texto = findViewById(R.id.text_principal);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        String emailDigitado = bundle.getString(LoginActivity.CHAVE_EMAIL);

        texto.setText(emailDigitado);
    }
}
