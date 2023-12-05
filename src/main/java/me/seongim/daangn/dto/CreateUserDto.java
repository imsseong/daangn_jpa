package me.seongim.daangn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.seongim.daangn.domain.entity.user.*;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class CreateUserDto {

    @Getter
    @Setter
    public static class Request {

        private String email;
        private String password;

        private String phoneNumber;

        private String nickname;

        private String imageUrl;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .profile(new Profile(nickname, imageUrl))
                    .roleType(UserRoleType.USER)
                    .status(UserStatus.NORMAL)
                    .build();
        }
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

        public static CreateUserDto.Response from(User user) {
            return CreateUserDto.Response.builder()
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
