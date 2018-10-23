package com.digitalhouse.digitalhouseapp.adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.model.Post;

import java.util.List;

public class RecyclerViewPostAdapter extends RecyclerView.Adapter<RecyclerViewPostAdapter
        .ViewHolder> {

    private List<Post> postList;
    private CardPostClicado listener;

    public interface CardPostClicado {
        void onCardClicado(Post post);
    }

    public RecyclerViewPostAdapter(List<Post> postList, CardPostClicado listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .content_post_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Post post = postList.get(position);
        viewHolder.bind(post);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView descricao;
        private ImageView imagem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.text_view_title_post_id);
            descricao = itemView.findViewById(R.id.text_post_description_id);
            imagem = itemView.findViewById(R.id.image_view_post_id);
        }

        public void bind(final Post post) {
            titulo.setText(post.getTitulo());
            descricao.setText(post.getDescricao());

            imagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCardClicado(post);
                }
            });
        }
    }
}
