package me.seongim.daangn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import me.seongim.daangn.domain.entity.user.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDto {

    @Getter
    public static class Request {

        private String email;
        private String password;
    }

    @Builder
    @Getter
    public static class Response {

        private Long id;

        private String email;

        private String phoneNumber;

        private String nickname;

        private String imageUrl;

        @Enumerated(EnumType.STRING)
        private UserRoleType roleType;

        @Enumerated(EnumType.STRING)
        private UserStatus status;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<UserRegion> userRegions = new ArrayList<>();

        private String loginIp;

        private String token;

        public static Response from(User user) {
            return Response.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .nickname(user.getProfile().getNickname())
                    .imageUrl(user.getProfile().getImageUrl())
                    .roleType(user.getRoleType())
                    .status(user.getStatus())
                    .userRegions(user.getUserRegions())
                    .loginIp(user.getLoginIp())
                    .token(user.getToken())
                    .build();
        }
    }
}
