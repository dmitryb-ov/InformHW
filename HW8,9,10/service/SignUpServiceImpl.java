package com.example.demo.service;

import com.example.demo.dto.SignUpDto;
import com.example.demo.entitymanager.UserDAO;
import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserDAO userDAO;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto form) {
        User user = User.builder()
                .name(form.getName())
                .secondName(form.getSecondName())
                .password(/*passwordEncoder.encode(*/form.getPassword())/*)*/
                .bDate(form.getBDate())
                .build();
//        usersRepository.save(user);
        User completeUser = userDAO.add(user);
    }
}
