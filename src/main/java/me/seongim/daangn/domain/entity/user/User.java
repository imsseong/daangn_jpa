package me.seongim.daangn.domain.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import me.seongim.daangn.domain.entity.BaseEntity;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Table(name = "users")
@Audited
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    private Profile profile;

    @Enumerated(EnumType.STRING)
    private UserRoleType roleType;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    //@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED) //이력 대상 제외
    @NotAudited
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRegion> userRegions = new ArrayList<>();

    private LocalDateTime lastLoginAt;

    @Setter
    @JsonIgnore
    private String loginIp;

    @JsonIgnore
    private String token;

    public User(String loginIp) {
        this.loginIp = loginIp;
    }

    @Builder
    public User(Long id, String email, String password, String phoneNumber, Profile profile, UserRoleType roleType, UserStatus status, List<UserRegion> userRegions) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profile = profile;
        this.roleType = roleType;
        this.status = status;
        this.userRegions = userRegions;
    }

    public void addUserRegion(UserRegion userRegion) {
        userRegions.add(userRegion);
        userRegion.setUser(this);
    }

    public void login(String loginIp, String token) {
        this.loginIp = loginIp;
        this.token = token;
        lastLoginAt = LocalDateTime.now();
    }
}
