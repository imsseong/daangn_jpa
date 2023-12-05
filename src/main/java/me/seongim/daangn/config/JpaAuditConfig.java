package me.seongim.daangn.config;

import me.seongim.daangn.domain.entity.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.Random;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditConfig {

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAwareImpl();
    }

    //등록자, 수정자에 사용될 사용자 정보를 불러온 후 리턴
    public class AuditorAwareImpl implements AuditorAware<Long> {
        @Override
        public Optional<Long> getCurrentAuditor() {
            //로그인한 사용자의 정보
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(null == user) {
                return null;
            }

            return Optional.of(user.getId() == null ? 0 : user.getId());
        }

    }
}
