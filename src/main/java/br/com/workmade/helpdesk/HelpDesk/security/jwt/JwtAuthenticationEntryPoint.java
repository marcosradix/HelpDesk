package br.com.workmade.helpdesk.HelpDesk.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import br.com.workmade.helpdesk.HelpDesk.util.SerializeUtil;

@Component
public class JwtAuthenticationEntryPoint extends SerializeUtil implements AuthenticationEntryPoint {

	private static final long serialVersionUID = -5499387594226296822L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
	}

}
