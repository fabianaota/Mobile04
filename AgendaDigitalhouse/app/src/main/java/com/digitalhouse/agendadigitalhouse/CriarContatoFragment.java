package com.digitalhouse.agendadigitalhouse;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.digitalhouse.agendadigitalhouse.model.Contato;


/**
 * A simple {@link Fragment} subclass.
 */
public class CriarContatoFragment extends Fragment {

    CriarContatoActions listener;

    public CriarContatoFragment() {
        // Required empty public constructor
    }

    public interface CriarContatoActions{
        void criarContato(Contato contato);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CriarContatoActions){
            listener = (CriarContatoActions) context;
        }else{
            throw new ClassCastException("Activity não é implementa CriarContatoActions");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_criar_contato, container, false);

        Button buttonCriar = view.findViewById(R.id.button_criar_id);
        final TextInputEditText editTextNome = view.findViewById(R.id.edit_text_nome_id);
        final TextInputEditText editTextTelefone = view.findViewById(R.id.edit_text_telefone_id);

        buttonCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criar(editTextNome, editTextTelefone);
            }
        });

        return view;
    }

    private void criar(TextInputEditText editTextNome, TextInputEditText editTextTelefone) {
        Contato contato = new Contato();
        contato.setNome(editTextNome.getEditableText().toString());
        contato.setTelefone(editTextTelefone.getEditableText().toString());

        listener.criarContato(contato);

    }

}
