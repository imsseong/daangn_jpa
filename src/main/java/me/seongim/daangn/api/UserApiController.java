package me.seongim.daangn.api;

import lombok.RequiredArgsConstructor;
import me.seongim.daangn.domain.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;
/*
    @GetMapping("/api/v1/users")
    public ApiResult<MemberTeamDto> searchUserV1(MemberSearchCondition condition) {
        return memberJpaRepository.search(condition);
    }*/
}
