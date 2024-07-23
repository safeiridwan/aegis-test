package com.aegis.service.user;

import com.aegis.controller.user.request.CreateUserRequest;
import com.aegis.model.User;
import com.aegis.repository.UserRepository;
import com.aegis.response.ResponseAPI;
import com.aegis.util.PasswordGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.aegis.util.constant.ResponseMessage.OK;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordGeneratorUtil passwordGeneratorUtil;
    @Override
    public ResponseEntity<ResponseAPI> createUser(CreateUserRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            return new ResponseEntity<>(new ResponseAPI(400, "User already Exist", null, null), HttpStatus.BAD_REQUEST);
        }

        user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setFullname(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(passwordGeneratorUtil.generatePassword(8)));
        userRepository.save(user);

        // Kirim email

        return new ResponseEntity<>(new ResponseAPI(200, OK, null, null), HttpStatus.OK);
    }
}
