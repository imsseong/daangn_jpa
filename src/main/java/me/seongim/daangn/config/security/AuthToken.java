package me.seongim.daangn.config.security;

import me.seongim.daangn.domain.entity.user.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthToken extends AbstractAuthenticationToken {

    private final User user;

    public AuthToken(Collection<? extends GrantedAuthority> authorities, User user) {
        super(authorities);
        this.user = user;
    }

    @Override
    public String getCredentials() {
        return user.getPhoneNumber();
    }

    @Override
    public User getPrincipal() {
        return user;
    }
}
