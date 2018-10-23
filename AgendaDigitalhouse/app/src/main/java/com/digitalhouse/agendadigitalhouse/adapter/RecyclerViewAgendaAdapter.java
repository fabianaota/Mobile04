package com.digitalhouse.agendadigitalhouse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.digitalhouse.agendadigitalhouse.R;
import com.digitalhouse.agendadigitalhouse.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAgendaAdapter extends RecyclerView.Adapter<RecyclerViewAgendaAdapter.ViewHolder> {

    private List<Contato> contatoList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_contato_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Contato contato = contatoList.get(i);
        viewHolder.bind(contato);
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    public void addContato(Contato contato){
        contatoList.add(contato);
        notifyItemChanged(getItemCount());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNome;
        TextView textTelefone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.content_nome_id);
            textTelefone = itemView.findViewById(R.id.content_telefone_id);

        }

        public void bind(Contato contato) {
            textNome.setText(contato.getNome());
            textTelefone.setText(contato.getTelefone());
        }
    }

}
