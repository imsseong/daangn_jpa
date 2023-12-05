package me.seongim.daangn.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private String nickname;

    private String imageUrl;

    public void update(Profile profile) {
        if (StringUtils.hasText(profile.getImageUrl())) {
            imageUrl = profile.getImageUrl();
        }
        nickname = profile.getNickname();
    }
}
