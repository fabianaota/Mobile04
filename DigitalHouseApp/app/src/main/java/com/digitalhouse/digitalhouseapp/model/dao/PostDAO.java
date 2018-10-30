package com.digitalhouse.digitalhouseapp.model.dao;

import android.app.Activity;
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

public class PostDAO {

    public List<Post> getPostList(Context context, final ServiceListener listener) {
        List<Post> postList = new ArrayList<>();

        Call<List<Post>> call = RetrofitService.getPostApi().getPosts();

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.body() != null){
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                listener.onError(t);
            }
        });

        return postList;
    }

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
