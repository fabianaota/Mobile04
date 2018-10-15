package com.digitalhouse.digitalhouseapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.model.Post;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    public interface PostClicado {

        void onButtonClick(Post post);

    }

    private PostClicado listener;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) { // context == Activity que contem o Fragment
        super.onAttach(context);

        if(context instanceof PostClicado){
            listener = (PostClicado) context;
        }else{
            throw new ClassCastException("A activity não é uma instancia de Post Clicado");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        Button botaoDetalhe = view.findViewById(R.id.button_post_detail_id);

        botaoDetalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post = new Post();
                post.setTitulo("Titulo do post");
                post.setDescricao("Descricao do post");
                post.setAutor("Autor 1");
                post.setDataCriacao(new Date());
                post.setImagemUrl("www.google.com/imageX");

                listener.onButtonClick(post);
            }
        });

        return view;
    }
}
