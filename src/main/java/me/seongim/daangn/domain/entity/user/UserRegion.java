package me.seongim.daangn.domain.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRegion {

    @Id
    @GeneratedValue
    @Column(name = "user_region_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    // 동네 인증
    private boolean isAuthenticated;

    // 대표 동네 설정 상태
    private boolean isRepresent;

    public static UserRegion createUserRegion(User user, Region region) {
        UserRegion userRegion = new UserRegion();
        userRegion.setUser(user);
        userRegion.setRegion(region);
        userRegion.setRepresent(true);
        return userRegion;
    }

    /**
     * 동네 삭제
     */
    public void delete() {
        user.getUserRegions().remove(this);
    }

}