package com.lit.app.config.security;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("ajaxAuthenticationSuccessHandler")
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		String url = "";
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals("ROLE_ADMIN"))
				url = "admin";
			else if (authority.getAuthority().equals("ROLE_USER"))
				url = "user";
			else	url = "accessDenied";
		}
		response.getWriter().print(url);
		response.getWriter().flush();
	}
}
