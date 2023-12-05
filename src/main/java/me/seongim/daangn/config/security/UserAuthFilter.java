package me.seongim.daangn.config.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.seongim.daangn.domain.entity.user.User;
import me.seongim.daangn.domain.repository.UserRepository;
import me.seongim.daangn.util.RequestUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {
        //Request Header 에서 토큰 정보 추출
        String token = httpServletRequest.getHeader("d-authorization");
        //Request Header 에서 client ip 추출
        String ipAddress = RequestUtil.getClientIpAddress();

        User user = null;
        boolean isAuthenticated = false;
        try {
            if (StringUtils.hasText(token)) {
                user = userRepository.findByToken(token);
                if (user != null) {
                    isAuthenticated = true;
                    user.setLoginIp(ipAddress);
                }
            }
        } catch (Exception e) {
            log.error("[EXCEPTION: Could not set user authentication in security context]", e);
        }
        if (!isAuthenticated) {
            user = new User(ipAddress);
        }
        AuthToken authToken = new AuthToken(isAuthenticated ? Collections.singletonList(new SimpleGrantedAuthority(user.getRoleType().name())) : null, user);
        authToken.setAuthenticated(isAuthenticated);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
