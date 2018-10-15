package com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.fragments.PeopleFragment;
import com.digitalhouse.digitalhouseapp.fragments.PostsFragment;
import com.digitalhouse.digitalhouseapp.interfaces.PostClicado;

public class MainActivity extends AppCompatActivity implements PostClicado {

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

    public void exibirPosts(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_id, new PostsFragment());
        transaction.commit();
    }

    public void exibirPessoas(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_id, new PeopleFragment());
        transaction.commit();
    }

    @Override
    public void onButtonClick(String teto) {

    }
}
