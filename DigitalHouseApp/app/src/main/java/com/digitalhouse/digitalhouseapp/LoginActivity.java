package com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String CHAVE_EMAIL = "chave_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    // Metodo definido no onClick do XML (activity_login.xml)
    public void loginClicado(View view) {
        // Criar um intent para navegação para home
        Intent intent = new Intent(this, HomeActivity.class);

        // Criar um bundle (pacote) para enviar os dados
        Bundle bundle = new Bundle();

        final TextInputEditText emailDigitado = findViewById(R.id.edit_text_email_id);
        final TextInputEditText passwordDigitado = findViewById(R.id.edit_text_password_id);

        Button buttonLogin = findViewById(R.id.button_login_id);

        // Verificação (mock/fake) se o email digitado é igual a senha digitada
        // Para comparar os valores digitados é necessário chamar o método getText().toString()
        // dos campos do tipo TextInputEditText
        if (emailDigitado.getText().toString().equals(passwordDigitado.getText().toString())) {
            // Se passar na validação, enviar o e-mail digital através do bundle
            bundle.putString(CHAVE_EMAIL, emailDigitado.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            // Se não passar na validação, mudar a cor do campos e exiber msg de erro
            emailDigitado.setTextColor(getResources().getColor(R.color.error));
            passwordDigitado.setTextColor(getResources().getColor(R.color.error));

            Snackbar.make(buttonLogin, "Email e/ou senha incorreto(s)", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Ok, entendi.", new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            emailDigitado.setTextColor(getResources().getColor(R.color.primary_text));
                            passwordDigitado.setTextColor(getResources().getColor(R.color.primary_text));
                        }
                    })
                    .show();
        }

    }
}
