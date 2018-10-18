package com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.fragments.PeopleFragment;
import com.digitalhouse.digitalhouseapp.fragments.PostDetailFragment;
import com.digitalhouse.digitalhouseapp.fragments.PostsFragment;
import com.digitalhouse.digitalhouseapp.model.Post;

public class MainActivity extends AppCompatActivity implements PostsFragment.PostClicado {

    public final static String POST_TITLE = "POST_TITLE";
    public final static String POST_DESCRIPTION = "POST_DESCRIPTION";

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
    public void onButtonClick(Post post) {
        // Crio bundle
        Bundle bundle = new Bundle();

        // Coloco o titulo numa chave chamada POST_TITLE
        bundle.putString(POST_TITLE, post.getTitulo());

        // Coloco o titulo numa chave chamada POST_TITLE
        bundle.putString(POST_DESCRIPTION, post.getDescricao());

        // Crio FragmentManager e FragmentTransaction
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // Crio Post Detail Fragment
        PostDetailFragment detailFragment = new PostDetailFragment();

        // Defino que o argument é o bundle criado
        detailFragment.setArguments(bundle);

        // Através da transaction eu troco o fragment do container
        transaction.replace(R.id.container_id, detailFragment);

        // Efetivo a transacao
        transaction.commit();
    }
}
