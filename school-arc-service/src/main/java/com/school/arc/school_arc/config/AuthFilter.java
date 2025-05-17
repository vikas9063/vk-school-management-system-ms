package com.school.arc.school_arc.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.school.arc.school_arc.dto.Constants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String xAuth = request.getHeader("X-AUTH");

		if (xAuth == null || xAuth.isEmpty()) {
			setErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Missing X-AUTH header");
			return;
		}

		try {
			// You can validate/parse xAuth here if needed
			String user = xAuth;
			request.setAttribute(Constants.LOGGED_IN_USER, user);

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			setErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid X-AUTH value");
		}
	}

	private void setErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String json = String.format("{\"status\":\"%d\",\"message\":\"%s\",\"result\":null}", status, message);

		response.getWriter().write(json);
	}

}
