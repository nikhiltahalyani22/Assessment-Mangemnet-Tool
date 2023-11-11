package io.nikks.loginservice.service;


public interface LoginService {


    String getUserType(Long userId);

    String loginCheck(Long username, String password);
}
