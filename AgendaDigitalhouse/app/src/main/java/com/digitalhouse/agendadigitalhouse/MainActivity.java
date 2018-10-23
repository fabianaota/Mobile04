package com.digitalhouse.agendadigitalhouse;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.digitalhouse.agendadigitalhouse.adapter.RecyclerViewAgendaAdapter;
import com.digitalhouse.agendadigitalhouse.model.Contato;

public class MainActivity extends AppCompatActivity implements CriarContatoFragment.CriarContatoActions {

    private RecyclerViewAgendaAdapter adapter;
    private CriarContatoFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab_id);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                fragment = new CriarContatoFragment();
                transaction.replace(R.id.container_id, fragment);
                transaction.commit();
            }
        });
    }

    private void setupRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recyclerview_contatos_id);
        adapter = new RecyclerViewAgendaAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void criarContato(Contato contato) {
        adapter.addContato(contato);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.remove(fragment);

        transaction.commit();
    }
}
