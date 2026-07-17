package com.innobliss.reservego_router;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ApiKeyValidationFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "api-key";
    private static final String VALID_API_KEY = "DEVOURINh9ko4E6AMpLeJay6bWklyE6KDmX9TlYFKpIQTikRJyBeevQvj1vRBZtzNHD1l3DAfjsz2NyCBQhAxk2lxfsx98w8jAHx2UJI54CISjy8dyIn5mEG9wRpA4ew";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {

       String apiKey = request.getHeader(API_KEY_HEADER);

       if (apiKey != null && VALID_API_KEY.equals(apiKey.trim())) {
          filterChain.doFilter(request, response); // allow request
       } else {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("Invalid API Key");
       }
    }
}