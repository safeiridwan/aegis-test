package com.aegis.service.user;

import com.aegis.controller.user.request.CreateUserRequest;
import com.aegis.controller.user.request.UpdateUserRequest;
import com.aegis.controller.user.response.DetailUserResponse;
import com.aegis.model.User;
import com.aegis.repository.UserRepository;
import com.aegis.response.ResponseAPI;
import com.aegis.util.PasswordGeneratorUtil;
import com.aegis.util.mail.EmailDetail;
import com.aegis.util.mail.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.aegis.util.constant.ResponseMessage.OK;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordGeneratorUtil passwordGeneratorUtil;
    private final MailUtil mailUtil;

    @Override
    public ResponseEntity<ResponseAPI> createUser(CreateUserRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            return new ResponseEntity<>(new ResponseAPI(400, "User already Exist", null, null), HttpStatus.BAD_REQUEST);
        }

        user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        String password = passwordGeneratorUtil.generatePassword(8);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole(request.getRole());
        userRepository.save(user);

        EmailDetail mail = new EmailDetail(user.getEmail(), password);
        mailUtil.send(mail);

        return new ResponseEntity<>(new ResponseAPI(200, OK, null, new DetailUserResponse(user)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAPI> updateUser(String userId, UpdateUserRequest request) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity<>(new ResponseAPI(400, "User not found.", null, null), HttpStatus.BAD_REQUEST);
        }

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseAPI(200, OK, null, new DetailUserResponse(user)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAPI> listUser() {
        List<DetailUserResponse> res = userRepository.findAll()
                .stream()
                .map(DetailUserResponse::new)
                .toList();
        return new ResponseEntity<>(new ResponseAPI(200, OK, null, res), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAPI> detailUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity<>(new ResponseAPI(400, "User not found.", null, null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ResponseAPI(200, OK, null, new DetailUserResponse(user)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAPI> deleteUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity<>(new ResponseAPI(400, "User not found.", null, null), HttpStatus.BAD_REQUEST);
        }

        userRepository.delete(user);
        return new ResponseEntity<>(new ResponseAPI(200, OK, null, null), HttpStatus.OK);
    }
}
