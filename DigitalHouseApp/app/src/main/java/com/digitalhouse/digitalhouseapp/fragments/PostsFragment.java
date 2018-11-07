package com.digitalhouse.digitalhouseapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.adapter.RecyclerViewPostAdapter;
import com.digitalhouse.digitalhouseapp.model.Post;
import com.digitalhouse.digitalhouseapp.model.dao.PostDAO;
import com.digitalhouse.digitalhouseapp.service.ServiceListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment implements RecyclerViewPostAdapter.CardPostClicado,
        ServiceListener {

    private RecyclerViewPostAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    // Inicialmente, não deve ser "pulado" nenhum item na busca paginada
    private int offset = 0;

    @Override
    public void onCardClicado(Post post) {
        // Este método é da implementação da interface CardPostClicado e faz parte da comunicação
        // do RecyclerView com este Fragment
        listener.goToFragmentDetails(post);
    }

    @Override
    public void onShareClicado(Post post) {
        ShareDialog shareDialog = new ShareDialog(this);

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(post.getImagemUrl()))
                .setQuote(post.getTitulo())
                .build();
        shareDialog.show(content);
    }

    // Esta interface é da comunicação entre Fragments
    // A comunicação deve ser feita entre Fragment --interface--> Activity --bundle--> Fragment
    public interface ComunicacaoPostFragment {
        void goToFragmentDetails(Post post);
    }

    // Este atributo será responsável por efetuar a comunicação deste Fragment com a Activity
    // que implementa a interface
    private ComunicacaoPostFragment listener;

    public PostsFragment() {
        // O construtor vazio é obrigatório no Fragment (NÃO APAGAR)
    }

    @Override
    public void onAttach(Context context) { // context == Activity que contem o Fragment
        super.onAttach(context);

        // Sobrescrever o método onAttach para receber a implementação da interface (Activity)
        if (context instanceof ComunicacaoPostFragment) {
            listener = (ComunicacaoPostFragment) context;
        } else {
            // Caso este fragment seja chamado por alguma activity que não implemente a interface
            // ComunicacaoPostFragment, deve ser lançada uma exceção pois é de um erro de fluxo
            throw new ClassCastException("A activity não é uma instancia de " +
                    "ComunicacaoPostFragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Define o XML (layout) do Fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        // Associa o ProgressBar (loader) a um atributo
        progressBar = view.findViewById(R.id.progress_bar_id);

        // Método responsável por fazer o setup das views que são clicáveis
        setupOnClickListeners(view);

        // Método responsável para fazer o setup da lista (RecyclerView)
        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view) {

        // Associa o RecyclerView a um atributo através do ID
        recyclerView = view.findViewById(R.id.recyclerview_posts_id);

        // Cria um novo PostDAO (responsável pela busca de dados)
        // Esta variável deve ser final pois será utilizada dentro de uma implementação anônima
        final PostDAO dao = new PostDAO();

        // Cria e define o Adapter
        // Já faz a busca dos Posts através da classe DAO
        adapter = new RecyclerViewPostAdapter(dao.getPostList(getContext(), this, offset), this);
        recyclerView.setAdapter(adapter);

        // Define layout manager que é o responsável pela disposição das células na lista
        // Pode ser um LinearLayoutManager (vertical ou horizontal) ou um GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // Define um listener para saber se a lista já foi "scrollada" até o final
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // Se a lista atingiu o final do scroll
                if (!recyclerView.canScrollVertically(1)) {
                    // Tornar o progressBar visivel
                    progressBar.setVisibility(View.VISIBLE);

                    // Aumentar o offset (quantidade de itens que são "pulados" na busca paginada
                    // O offset sempre aumenta de acordo com a constante POST_LIMIT definida no dao
                    offset = PostDAO.POST_LIMIT + offset;

                    // Chamar o método que busca Posts de forma paginada, porém com o novo offset
                    dao.getPostList(getContext(), PostsFragment.this, offset);
                }
            }
        });

    }

    private void setupOnClickListeners(View view) {
        FloatingActionButton fab = view.findViewById(R.id.fab_plus_id);

        // Define onClickListener para o Floating Action Button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Novo post", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // TODO criar post
                            }
                        })
                        .show();
            }
        });
    }

    // Os métodos abaixo (onSuccess e onError) são a implementação da interface ServiceListener
    // para implementar a chamada assíncrona do Retrofit

    @Override
    public void onSuccess(Object object) {
        // caso a Request no Retrofit tenha tido sucesso, acrescentar a listagem de Posts no final
        // da lista. Esta lista é gerenciada pelo adapter
        List<Post> postList = (List<Post>) object;
        adapter.addPostList(postList);

        // Após acrescentar a lista, o progressBar (loader) deve ficar invisível
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable throwable) {
        // Exibir SnackBar com o erro
        Snackbar.make(recyclerView, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
        // Em caso de erro o progressBar (loader) também deve ficar invisível
        progressBar.setVisibility(View.GONE);
    }

}
