package com.digitalhouse.meuprimeiroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        Toast.makeText(this, "Método onCreate() foi chamado", Toast.LENGTH_LONG).show();
        Log.e("PrimeiroApp", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Método onStart() foi chamado", Toast.LENGTH_LONG).show();
        Log.e("PrimeiroApp", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Método onResume() foi chamado", Toast.LENGTH_LONG).show();
        Log.e("PrimeiroApp", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Método onRestart() foi chamado", Toast.LENGTH_LONG).show();
        Log.e("PrimeiroApp", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Método onPause() foi chamado", Toast.LENGTH_LONG).show();
        Log.e("PrimeiroApp", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Método onStop() foi chamado", Toast.LENGTH_LONG).show();
        Log.e("PrimeiroApp", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Método onDestroy() foi chamado", Toast.LENGTH_LONG).show();
        Log.e("PrimeiroApp", "onDestroy");
    }

    public void botaoClicado(View view) {
        float imc = 0F;
        float peso = Float.parseFloat(pesoDigitado.getText().toString());
        float altura = Float.parseFloat(alturaDigitada.getText().toString());

        imc = peso / (altura * altura);

        texto.setText("Seu IMC é: "+ imc);
    }
}
