package com.digitalhouse.agendadigitalhouse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.digitalhouse.agendadigitalhouse.R;
import com.digitalhouse.agendadigitalhouse.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAgendaAdapter extends RecyclerView.Adapter<RecyclerViewAgendaAdapter.ViewHolder> implements Filterable {

    private List<Contato> contatoList = new ArrayList<>();
    private List<Contato> filteredList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_contato_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Contato contato = filteredList.get(i);
        viewHolder.bind(contato);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void addContato(Contato contato){
        contatoList.add(contato);
        filteredList.add(contato);
        notifyItemChanged(getItemCount());
    }

    public void removeAll() {
        contatoList.clear();
        filteredList.clear();
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                filteredList = new ArrayList<>();
                String search = charSequence.toString();
                if(search.isEmpty()){
                    filteredList.addAll(contatoList);
                }else{
                    for (Contato contato : contatoList) {
                        if(contato.getNome().contains(search)){
                            filteredList.add(contato);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Contato>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
