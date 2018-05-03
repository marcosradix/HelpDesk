package br.com.workmade.helpdesk.HelpDesk.security.jwt;

import br.com.workmade.helpdesk.HelpDesk.util.SerializeUtil;

public class JwtAuthenticationRequest extends SerializeUtil{

	
	private static final long serialVersionUID = -554861948775514124L;

	private String email;
	private String password;
	
	public JwtAuthenticationRequest() {
		super();
		
	}

	public JwtAuthenticationRequest(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
