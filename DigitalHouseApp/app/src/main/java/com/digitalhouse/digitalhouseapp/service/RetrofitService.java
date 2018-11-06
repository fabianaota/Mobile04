package com.digitalhouse.digitalhouseapp.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Configuração do Retrofit
// Esta classe foi criada para que a configuração possa ser utilizada em no projeto inteiro
// sem a necessidade de copiar e colar este código
public class RetrofitService {

    // A URL base da API
    private static final String BASE_URL = "https://digitalhouse.herokuapp.com/";
    private static Retrofit retrofit;

    // Cria a configuração do Retrofit
    public static Retrofit getRetrofit(){
        if(retrofit == null){
            // Define os tempos de timeout (espera antes de erro) para cada caso
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);

            // Cria o retrofit utilizando a URL base, a configuração de timeout e o GSON como
            // conversor de Json -> Pojo
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

        }
        return retrofit;
    }

    // Cria a configuração especifica para a chamada da API de Posts
    public static PostAPI getPostApi(){
        return getRetrofit().create(PostAPI.class);
    }

}
