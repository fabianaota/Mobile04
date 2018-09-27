package com.digitalhouse.meuprimeiroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PrimeiraActivity extends AppCompatActivity {

    private EditText pesoDigitado;
    private EditText alturaDigitada;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira);

        pesoDigitado = findViewById(R.id.peso_digitado_id);
        alturaDigitada = findViewById(R.id.altura_digitada_id);
        texto = findViewById(R.id.text_view_id);
    }

    public void botaoClicado(View view) {
        float imc = 0F;
        float peso = Float.parseFloat(pesoDigitado.getText().toString());
        float altura = Float.parseFloat(alturaDigitada.getText().toString());

        imc = peso / (altura * altura);

        texto.setText("Seu IMC é: "+ imc);
    }
}
