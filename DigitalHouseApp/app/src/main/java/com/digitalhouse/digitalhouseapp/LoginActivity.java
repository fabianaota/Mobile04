package com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String CHAVE_EMAIL = "chave_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginClicado(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        Bundle bundle = new Bundle();

        TextInputEditText emailDigitado = findViewById(R.id.edit_text_email_id);
        TextInputEditText passwordDigitado = findViewById(R.id.edit_text_password_id);

        if(emailDigitado.getText().toString().equals("fabio@digital.com") &&
                passwordDigitado.getText().toString().equals("123")){
            bundle.putString(CHAVE_EMAIL, emailDigitado.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Email e/ou senha incorreto(s)", Toast.LENGTH_SHORT).show();
        }

    }
}
