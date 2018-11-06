package com.digitalhouse.digitalhouseapp.model.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.digitalhouse.digitalhouseapp.model.Post;
import com.digitalhouse.digitalhouseapp.service.RetrofitService;
import com.digitalhouse.digitalhouseapp.service.ServiceListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// No modelo MVC as classes do tipo DAO fazem parte da Model (Model View Controller)
// e são responsáveis pelo acesso aos dados (Data Access Object), sejam estes dados
// remotos (Retrofit) ou locais (Json/GSON, SQLite)
public class PostDAO {

    // Constante que define o limite de Posts buscados de forma paginada
    public static final int POST_LIMIT = 3;

    // Este método é responsável por buscar a listagem de posts
    public List<Post> getPostList(Context context, final ServiceListener listener, int offset) {
        List<Post> postList = new ArrayList<>();

        // Chamada da API através da classe de configuração do Retrofit
        // Para a requisição paginada, deve ser enviados:
        // - offset (quantidade de itens a serem pulados
        // - limit (quantidade de itens a serem retornados
        Call<List<Post>> call = RetrofitService.getPostApi().getPostsPage(offset, POST_LIMIT);

        // Por se tratar de uma requisição assíncrona, é necessário chamar o método enqueue e
        // criar uma implementação anonima de Callback
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.body() != null) {
                    // Caso haja sucesso na requisição, utilizar o listener
                    // (ServiceListener -recebido como parâmetro) como sucesso
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Caso haja erro na requisição, utilizar o listener
                // (ServiceListener -recebido como parâmetro) como erro
                listener.onError(t);
            }
        });

        return postList;
    }

    // Buscar dados em um Json local (este Json deve estar na pasta assets
    private List<Post> getLocalPosts(Context context) {
        try {
            // Abrir arquivo
            AssetManager manager = context.getAssets();
            InputStream postJson = manager.open("posts.json");

            // Ler arquivo
            InputStreamReader inputStreamReader = new InputStreamReader(postJson);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Criar GSON
            Gson gson = new Gson();

            // Transformar Json em Pojo (array)
            Post[] postArray = gson.fromJson(bufferedReader, Post[].class);

            // converte array para ArrayList
            return Arrays.asList(postArray);

        } catch (IOException exception) {
            Log.e("PostDAO", "Erro ao ler arquivo");
        }
        return new ArrayList<>();
    }

}
