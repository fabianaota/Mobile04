package com.digitalhouse.digitalhouseapp.service;

public interface ServiceListener {

    void onSuccess(Object object);

    void onError(Throwable throwable);

}
