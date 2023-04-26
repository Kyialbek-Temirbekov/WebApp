package com.example.lofty.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Optional;

public class Cookies {
    public static String getValue(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookie;
        if(cookies!=null) {
            cookie = Arrays.stream(cookies).filter(
                    c -> "user".equals(c.getName())).findAny();
            if(cookie.isPresent())
                return cookie.get().getValue();
        }
        return null;
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> cookie = Arrays.stream(request.getCookies()).filter(
                    c -> "user".equals(c.getName())).findAny();
        if(cookie.isPresent()) {
            cookie.get().setMaxAge(0);
            response.addCookie(cookie.get());
        }
    }

    public static void add(String email, HttpServletResponse response) {
        Cookie cookie = new Cookie("user",email);
        cookie.setMaxAge(2600000);
        response.addCookie(cookie);
    }
}
