package com.example.simple_chatting.security.session;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.simple_chatting.security.session.SessionConst.USER_SESSION_KEY;

@Component
@RequiredArgsConstructor
public class UserSessionManager {

    private final HttpSession httpSession;

    public void saveUser(AccessUser accessUser) {
        httpSession.setAttribute(USER_SESSION_KEY, accessUser);
    }

    public AccessUser extractUser() {
        return (AccessUser) httpSession.getAttribute(USER_SESSION_KEY);
    }
}
