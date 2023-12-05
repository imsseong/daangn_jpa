package me.seongim.daangn.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RequestUtil {

    public static String getClientIpAddress() {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String clientIp = httpServletRequest.getHeader("x-forwarded-for");

        //proxy 환경일 경우
        if (!StringUtils.hasText(clientIp)) {
            clientIp = httpServletRequest.getHeader("Proxy-Client-IP");
        }

        //웹로직 서버일 경우
        if (!StringUtils.hasText(clientIp)) {
            clientIp = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }

        return StringUtils.hasText(clientIp) ? clientIp : httpServletRequest.getRemoteAddr();
    }
}
