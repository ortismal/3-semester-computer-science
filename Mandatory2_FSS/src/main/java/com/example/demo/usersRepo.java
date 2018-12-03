package com.example.demo;

import com.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface usersRepo extends CrudRepository<User, Long> {
    User findById(Long id);
}
