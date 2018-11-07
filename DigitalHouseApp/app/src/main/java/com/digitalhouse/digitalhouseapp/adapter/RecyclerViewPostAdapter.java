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
import com.squareup.picasso.Picasso;

import java.util.List;

// O Adapter tem como objetivo principal gerenciar a lista que sera utilizada pelo RecyclerView
public class RecyclerViewPostAdapter extends RecyclerView.Adapter<RecyclerViewPostAdapter
        .ViewHolder> {

    // Lista de POJO
    private List<Post> postList;

    // Interface/listener para comunição dos itens da lista com a Activity/Fragment
    public interface CardPostClicado {
        void onCardClicado(Post post);
        void onShareClicado(Post post);
    }

    // Propriedade do tipo da interface criada que sera usada efetivamente na comunicação
    private CardPostClicado listener;

    // O construtor recebe a listagem inicial e o listener
    public RecyclerViewPostAdapter(List<Post> postList, CardPostClicado listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Neste metodo é definido qual o XML (layout) sera utilizado em cada celula da lista
        // em seguida um novo ViewHolder é instanciado
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .content_post_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Neste método devemos recuperar o POJO da lista de acordo com a posição
        // e chamar o método bind (criar este metodo no ViewHolder) passando o POJO para
        // que seja feito o gerenciamento de cada celula
        Post post = postList.get(position);
        viewHolder.bind(post);
    }

    @Override
    public int getItemCount() {
        // devolver o tamanho da listagem
        return postList.size();
    }

    public void addPostList(List<Post> postList){
        // Este método foi criado para acrescentar itens no final da lista
        this.postList.addAll(postList);

        // Toda vez que a listagem é alterada, é necessário chamar um métooo de notificação
        // para que o RecyclerView seja atualizado
        notifyDataSetChanged();
    }

    // A classe interna ViewHolder gerencia o conteúdo de cada célula
    class ViewHolder extends RecyclerView.ViewHolder {

        // Todos as views que serão alteradas devem ser declaradas
        private TextView titulo;
        private TextView descricao;
        private ImageView imagem;
private ImageView share;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // No construtor, utilizar o itemView para fazer o findViewById
            titulo = itemView.findViewById(R.id.text_view_title_post_id);
            descricao = itemView.findViewById(R.id.text_post_description_id);
            imagem = itemView.findViewById(R.id.image_view_post_id);
            share = itemView.findViewById(R.id.imageView2);
        }

        public void bind(final Post post) {
            // O método bind foi criado para alterar os dados de cada célulara de acordo com o POJO
            // recebido como parâmetro
            titulo.setText(post.getTitulo());
            descricao.setText(post.getDescricao());

            // Para alterar imagens de acordo com uma URL, utilizar a biblioteca Picasso
            // http://square.github.io/picasso/ -> Para isso é necessário alterar o gradle
            Picasso.get()
                    .load(post.getImagemUrl())
                    .into(imagem);

            // Podemos definir aqui também ações como onClickListener. Para que essa ação seja
            // executada na activity/fragment, devemos utilizar o listener
            imagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCardClicado(post);
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onShareClicado(post);
                }
            });
        }
    }
}
