package com.digitalhouse.digitalhouseapp.fragments;


import android.os.Bundle;
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
public class PostsFragment extends Fragment {


    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        //setupOnClickListers(view);

        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view) {

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_posts_id);

        RecyclerViewPostAdapter adapter = new RecyclerViewPostAdapter(createPostList());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

    private List<Post> createPostList() {

        List<Post> postList = new ArrayList<>();

        Post post = new Post();
        post.setTitulo("Nova turma iniciada");
        post.setDescricao("Em outubro começou a nova turma de Marketing");
        postList.add(post);

        Post post1 = new Post();
        post1.setTitulo("Nova turma iniciada");
        post1.setDescricao("Em outubro começou a nova turma de Marketing");
        postList.add(post1);

        Post post2 = new Post();
        post2.setTitulo("Nova turma iniciada");
        post2.setDescricao("Em outubro começou a nova turma de Marketing");
        postList.add(post2);

        Post post3 = new Post();
        post3.setTitulo("Nova turma iniciada");
        post3.setDescricao("Em outubro começou a nova turma de Marketing");
        postList.add(post3);

        return postList;
    }

}
