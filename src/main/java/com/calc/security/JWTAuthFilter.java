package com.calc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.calc.data.UserDetailData;
import com.calc.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRES = 600_000;
    public static final String TOKEN_PASSWORD = "463408a1-54c9-4307-bb1c-6cced559f5a7";

    private final AuthenticationManager authenticationManager;

    public JWTAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            User usuario = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getLogin(),
                    usuario.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailData usuarioData = (UserDetailData) authResult.getPrincipal();

        String token = JWT.create().
                withSubject(usuarioData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}















