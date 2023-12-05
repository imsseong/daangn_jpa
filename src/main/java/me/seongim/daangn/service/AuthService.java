package me.seongim.daangn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.seongim.daangn.domain.entity.user.User;
import me.seongim.daangn.domain.repository.UserRepository;
import me.seongim.daangn.dto.CreateUserDto;
import me.seongim.daangn.dto.LoginDto;
import me.seongim.daangn.exception.CustomException;
import me.seongim.daangn.exception.ErrorCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 회원 가입
     */
    @Transactional
    public CreateUserDto.Response join(CreateUserDto.Request createUserRequest) {
        createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        User user = createUserRequest.toEntity();

        validateDuplicateUser(user); //중복 회원 검증

        userRepository.save(user);

        return CreateUserDto.Response.from(user);
    }

    private void validateDuplicateUser(User user) {
        Optional<User> findUsers = userRepository.findByEmail(user.getEmail());
        if (!findUsers.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    /**
     * 로그인
     */
    @Transactional
    public LoginDto.Response login(String email, String password, String loginIp) {
        Optional<User> users = userRepository.findByEmail(email);

        if (users.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        User user = users.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        String token = UUID.randomUUID().toString();
        user.login(loginIp, token);

        return LoginDto.Response.from(user);
    }


}
