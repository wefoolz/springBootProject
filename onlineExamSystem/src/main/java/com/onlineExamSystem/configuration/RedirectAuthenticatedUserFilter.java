package com.onlineExamSystem.configuration;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectAuthenticatedUserFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Get authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if user is authenticated and not anonymous
        if (authentication != null && authentication.isAuthenticated() && !isAnonymousUser(authentication)) {
            String requestURI = request.getRequestURI();

            // Check if the user is trying to access a public (anonymous) path
            if (requestURI.equals("/") || requestURI.equals("/online_exam_system/home")) {
                // Redirect based on the user's role
                if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
                    response.sendRedirect("/teacher/index");
                    return;
                } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
                    response.sendRedirect("/student/index");
                    return;
                }
            }
        }

        // Continue the filter chain for other cases
        filterChain.doFilter(request, response);
		
	}

	private boolean isAnonymousUser(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ANONYMOUS"));
    }
}
