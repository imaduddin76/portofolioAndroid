package com.limaefdua.bootcamp.auth;
//
// Created by mafuhin on 10/17/2019.
//

public interface LoginView {
    void onLoginSuccess(String username);

    void onError(String message);
}
