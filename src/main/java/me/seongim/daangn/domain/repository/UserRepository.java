package me.seongim.daangn.domain.repository;

import me.seongim.daangn.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long>, UserRepositoryCustom {

    //핵심 비지니스로직, 재사용성 있거나 엔티티 검색 혹은 특화된 경우 -> 기본은 MemberRepositoryCustom
    //공용성 없고 특정 api에 종속되어 있거나 등등 -> MemberQueryRepository 별도로 분리

    Optional<User> findByEmail(String email);

    Optional<User> findByProfile_nickname(String nickname);

    User findByToken(String token);

}
