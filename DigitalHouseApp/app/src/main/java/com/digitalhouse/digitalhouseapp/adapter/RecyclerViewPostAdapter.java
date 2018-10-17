package com.digitalhouse.digitalhouseapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.model.Post;

import java.util.List;

public class RecyclerViewPostAdapter extends RecyclerView.Adapter<RecyclerViewPostAdapter.ViewHolder> {

    private List<Post> postList;

    public RecyclerViewPostAdapter(List<Post> postList){
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_post_item, viewGroup, false);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.text_view_title_post_id);
            descricao = itemView.findViewById(R.id.text_post_description_id);
        }

        public void bind(Post post){
            titulo.setText(post.getTitulo());
            descricao.setText(post.getDescricao());
        }
    }
}
