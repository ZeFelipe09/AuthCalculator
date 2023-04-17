package com.calc.api;

import com.calc.domain.User;
import com.calc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserResources {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);

        return ResponseEntity.ok(user);
    }

}
