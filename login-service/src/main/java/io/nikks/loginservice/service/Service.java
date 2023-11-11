package io.nikks.loginservice.service;


import io.nikks.loginservice.entity.User;
import io.nikks.loginservice.repository.LoginRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service implements LoginService {

    private final LoginRepository loginRepository;


    @Override
    public String getUserType(Long userId) {
        Optional<User> user= loginRepository.findById(userId);
        if(user.isPresent()){
            return user.get().getType();
        }else {
            return "User not found";
        }

    }

    @Override
    public String loginCheck(Long username, String password) {
        Optional<User> theUser = loginRepository.findById(username);

        if(theUser.isEmpty()){
            return "User does not exist";
        }
        User user =theUser.get();

        if(!user.getPassword().equals(password)){
            return "Incorrect Password";
        }
        return user.getType().toLowerCase();
    }

}
