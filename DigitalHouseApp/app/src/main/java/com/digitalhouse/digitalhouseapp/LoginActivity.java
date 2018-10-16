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

    public void loginClicado(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        Bundle bundle = new Bundle();

        final TextInputEditText emailDigitado = findViewById(R.id.edit_text_email_id);
        final TextInputEditText passwordDigitado = findViewById(R.id.edit_text_password_id);

        Button bottonLogin = findViewById(R.id.button_login);

        if(emailDigitado.getText().toString().equals(passwordDigitado.getText().toString())){
            bundle.putString(CHAVE_EMAIL, emailDigitado.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Snackbar.make(bottonLogin, "Email ou usuario incorreto", Snackbar.LENGTH_LONG)
            .setAction("ok, entendi", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    emailDigitado.setText("");
                    passwordDigitado.setText("");
                }
            }).show();

        }


    }
}
