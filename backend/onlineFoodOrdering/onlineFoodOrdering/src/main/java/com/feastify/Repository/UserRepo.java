package com.feastify.Repository;

import com.feastify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    public User findByEmail(String username);
}
