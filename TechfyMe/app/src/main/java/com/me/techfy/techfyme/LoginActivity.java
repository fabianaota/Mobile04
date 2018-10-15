package com.me.techfy.techfyme;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
    public static final String CHAVE_EMAIL = "chave_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginClicado(View view) {

        Intent intent = new Intent(this, HomeActivity.class);
        Bundle bundle = new Bundle();

      TextInputEditText emailDigitado = findViewById(R.id.edit_text_email_id);
     TextInputEditText passwordDigitado = findViewById(R.id.edit_text_password_id);

     bundle.putString(CHAVE_EMAIL, emailDigitado.getText().toString());
     intent.putExtras(bundle);
     startActivity(intent);





    }
}
