package com.digitalhouse.digitalhouseapp.model.dao;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.digitalhouse.digitalhouseapp.model.Post;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostDAO {

    public List<Post> getPostList(Context context) {
        List<Post> postList = new ArrayList<>();

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
            postList = Arrays.asList(postArray);

        } catch (IOException exception) {
            Log.e("PostDAO", "Erro ao ler arquivo");
        }

        return postList;
    }

}
