package com.example.pathfinder.intercepotors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class IpBlackListInterceptor implements HandlerInterceptor {

    private final List<String> blackListAddresses = new ArrayList<>();

    public IpBlackListInterceptor() {
        this.blackListAddresses.add("127.0.0.1");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddresses = request.getRemoteAddr();
        System.out.println(ipAddresses);
        if (blackListAddresses.contains(ipAddresses)){
            response.sendRedirect("/error");
        }
        return true;
    }
}
