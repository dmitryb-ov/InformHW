package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
//    List<User> findAllByNameContainsAndCreatedAtBeforeOrEmailOrderByIdDesc(String name,String password,String bDate);
    User findByName(String name);
}
