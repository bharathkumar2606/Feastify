package com.feastify.service;

import com.feastify.Repository.UserRepo;
import com.feastify.config.JwtProvider;
import com.feastify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepo repo;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=repo.findByEmail(email);
        if (user == null) {
            throw new Exception("User Not Found");

        }
        return user;
    }
}
