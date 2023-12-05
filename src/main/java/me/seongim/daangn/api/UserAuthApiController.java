package me.seongim.daangn.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.seongim.daangn.domain.entity.user.User;
import me.seongim.daangn.dto.CreateUserDto;
import me.seongim.daangn.dto.LoginDto;
import me.seongim.daangn.exception.CustomException;
import me.seongim.daangn.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAuthApiController {

    private final AuthService authService;

    @PostMapping("/api/v1/users")
    public ResponseEntity<ApiResult<CreateUserDto.Response>> saveUser(@RequestBody @Valid CreateUserDto.Request createUserRequest) {
        try {
            CreateUserDto.Response createUserResponse = authService.join(createUserRequest);
            return ResponseEntity.ok(ApiResult.ok(createUserResponse));
        } catch (CustomException e) {
            log.error("", e);
            return ResponseEntity.ok(ApiResult.with(e.getErrorCode()));
        }
    }

    @PostMapping("/api/v1/users/login")
    public ResponseEntity<ApiResult<LoginDto.Response>> login(@AuthenticationPrincipal User user, @RequestBody LoginDto.Request loginRequest) {
        try {
            LoginDto.Response loginResponse = authService.login(loginRequest.getEmail(), loginRequest.getPassword(), user.getLoginIp());
            return ResponseEntity.ok(ApiResult.ok(loginResponse));
        } catch (CustomException e) {
            log.error("", e);
            return ResponseEntity.ok(ApiResult.with(e.getErrorCode()));
        }
    }


}