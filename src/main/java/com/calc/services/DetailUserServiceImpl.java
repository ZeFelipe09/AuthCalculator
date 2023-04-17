package com.calc.services;

import com.calc.data.UserDetailData;
import com.calc.domain.User;
import com.calc.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailUserServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public DetailUserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findUserByLogin(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User [" + username + "] not found");
        }

        return new UserDetailData(user);
    }

}
