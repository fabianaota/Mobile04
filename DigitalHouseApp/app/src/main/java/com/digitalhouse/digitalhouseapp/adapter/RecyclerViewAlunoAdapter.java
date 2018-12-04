package com.digitalhouse.digitalhouseapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAlunoAdapter extends RecyclerView.Adapter<RecyclerViewAlunoAdapter.ViewHolder> {

    private List<Aluno> listaAlunos = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .content_aluno_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Aluno aluno = listaAlunos.get(position);

        viewHolder.bind(aluno);

    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNome;
        private TextView textIdade;
        private TextView textId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textview_nome_id);
            textIdade = itemView.findViewById(R.id.textview_idade_id);
            textId = itemView.findViewById(R.id.textview_id_id);
        }

        public void bind(Aluno aluno) {
            textNome.setText(aluno.getNome());
            textId.setText(aluno.getId().toString());
            textIdade.setText(aluno.getIdade().toString());

            // TODO mandar pela interface (que nao esta criada) o ID (aluno.getId())
        }
    }
}
