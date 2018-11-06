package com.digitalhouse.digitalhouseapp.service;

// Esta é uma interface genérica e deve ser implementada em qualquer controller (Activity/Fragment)
// que faça chamadas na API.
// A utilização da interface é necessária devida as requisições serem feitas de forma assincrona
public interface ServiceListener {

    void onSuccess(Object object);

    void onError(Throwable throwable);

}
