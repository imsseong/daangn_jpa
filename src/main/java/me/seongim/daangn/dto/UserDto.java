package me.seongim.daangn.dto;

import lombok.*;
import me.seongim.daangn.domain.entity.user.Profile;
import me.seongim.daangn.domain.entity.user.User;
import me.seongim.daangn.domain.entity.user.UserRoleType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String email;

    private String password;

    private String phoneNumber;

    private String nickname;

    private String imageUrl;

    private UserRoleType roleType;

    public UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .nickname(user.getProfile().getNickname())
                .imageUrl(user.getProfile().getImageUrl())
                .roleType(user.getRoleType())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .profile(new Profile(nickname, imageUrl))
                .roleType(roleType)
                .build();
    }

}
