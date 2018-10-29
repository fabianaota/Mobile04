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
import com.digitalhouse.digitalhouseapp.model.dao.PostDAO;

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
            throw new ClassCastException("A activity não é uma instancia de ComunicacaoPostFragment");
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

        PostDAO dao = new PostDAO();

        // Criar e definir adapter
        RecyclerViewPostAdapter adapter = new RecyclerViewPostAdapter(dao.getPostList(getContext()), this);
        recyclerView.setAdapter(adapter);

        // Definir layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
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
