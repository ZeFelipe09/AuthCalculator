package com.calc.services;

import com.calc.domain.User;
import com.calc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public Integer saveUser(User user) {

        save(user);

        return user.getId();
    }
    private void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByLogin(String login){
        return userRepository.findUserByLogin(login);
    }
}
