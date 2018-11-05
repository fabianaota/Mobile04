package com.digitalhouse.digitalhouseapp.service;

import com.digitalhouse.digitalhouseapp.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PostAPI {

    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/posts/page")
    Call<List<Post>> getPostsPage(@Query("offset") int offset,
                                  @Query("limit") int limit);

}
