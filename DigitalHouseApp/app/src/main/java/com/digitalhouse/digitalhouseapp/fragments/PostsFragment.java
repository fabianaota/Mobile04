package com.digitalhouse.digitalhouseapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.interfaces.PostClicado;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {




    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof PostClicado){
        }else{
            throw new ClassCastException("A activity não é uma instancia de Post Clicado");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_posts, container, false);

       Button botaoDetalhe = view.findViewById(R.id.button_post_detail_id);
       botaoDetalhe.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(view.getContext(), "Indo para detalhes", Toast.LENGTH_SHORT).show();

               //TODO ir para detalhes

           }
       });

        return view;
    }

}
