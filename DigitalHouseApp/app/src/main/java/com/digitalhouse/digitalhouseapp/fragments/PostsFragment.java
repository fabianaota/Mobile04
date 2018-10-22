package com.digitalhouse.digitalhouseapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.adapter.RecyclerViewPostAdapter;
import com.digitalhouse.digitalhouseapp.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment implements RecyclerViewPostAdapter.CardPostClicado {

    @Override
    public void onCardClicado(Post post) {
        listener.goToFragmentDetails(post);
    }

    public interface ComunicacaoPostFragment {
        void goToFragmentDetails(Post post);
    }

    private ComunicacaoPostFragment listener;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) { // context == Activity que contem o Fragment
        super.onAttach(context);

        if(context instanceof ComunicacaoPostFragment){
            listener = (ComunicacaoPostFragment) context;
        }else{
            throw new ClassCastException("A activity não é uma instancia de Post Clicado");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        setupOnClickListeners(view);

        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view){

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_posts_id);

        // Criar e definir adapter
        RecyclerViewPostAdapter adapter = new RecyclerViewPostAdapter(createPostList(), this);
        recyclerView.setAdapter(adapter);

        // Definir layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    // MOCK - Simulação da busca na API ou no banco de dados
    private List<Post> createPostList(){
        List<Post> postList = new ArrayList<>();

        Post post = new Post();
        post.setTitulo("Nova turma iniciada");
        post.setDescricao("Em outubro começou a nova turma de Marketing");
        postList.add(post);

        Post post1 = new Post();
        post1.setTitulo("Estreia do novo cardápio");
        post1.setDescricao("A cafeteria agora tem um novo cardápior de almoço");
        postList.add(post1);

        Post post2 = new Post();
        post2.setTitulo("Inscrevam-se na Mentoria de Carreira");
        post2.setDescricao("Através do email mentoria@digitalhouse.com.br, inscreva-se!!");
        postList.add(post2);

        Post post3 = new Post();
        post3.setTitulo("Lembrete: Estudar Recycler View");
        post3.setDescricao("Não esquecer de estudar Recycler View no Co-Learning");
        postList.add(post3);

        return postList;
    }

    private void setupOnClickListeners(View view){
        FloatingActionButton fab = view.findViewById(R.id.fab_plus_id);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Novo post", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // TODO criar post
                            }
                        })
                        .show();
            }
        });
    }

}
