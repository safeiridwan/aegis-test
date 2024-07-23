package com.aegis.service;

import com.aegis.controller.auth.request.LoginRequest;
import com.aegis.model.User;
import com.aegis.repository.UserRepository;
import com.aegis.response.ResponseAPI;
import com.aegis.util.JwtHelperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.aegis.util.constant.ResponseMessage.OK;
import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository userRepository;
    private final JwtHelperUtil jwtUtil;
    @Override
    public ResponseEntity<ResponseAPI> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            return new ResponseEntity<>(new ResponseAPI(400, "User not found.", null, null), HttpStatus.BAD_REQUEST);
        }

        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if (!b.matches(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(new ResponseAPI(401, "Username or password invalid.", null, null), HttpStatus.UNAUTHORIZED);
        }

        Map<String, String> res = new HashMap<>();
        res.put("token", jwtUtil.generateToken(user.getUserId()));
        return new ResponseEntity<>(new ResponseAPI(200, OK, null, res), HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), emptyList());
    }
}
