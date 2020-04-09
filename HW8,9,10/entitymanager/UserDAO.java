package com.example.demo.entitymanager;

import com.example.demo.models.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@NoArgsConstructor
public class UserDAO {
    @Autowired
    private EntityManager entityManager;

    public User findById(Long id){
        return this.entityManager.find(User.class,id);
    }

    @Transactional
    public User add(User user){
        this.entityManager.persist(user);
        return user;
    }

    @Transactional
    public void updateUser(User user){
        this.entityManager.merge(user);
    }

    @Transactional
    public void delete(User user){
        this.entityManager.remove(user);
    }
}
