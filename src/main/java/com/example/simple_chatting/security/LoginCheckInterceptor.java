package com.example.simple_chatting.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.example.simple_chatting.security.SessionConst.USER_SESSION_KEY;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(USER_SESSION_KEY) == null) {
            response.sendRedirect("/?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
