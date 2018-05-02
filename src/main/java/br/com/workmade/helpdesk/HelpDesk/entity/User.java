package br.com.workmade.helpdesk.HelpDesk.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;

import br.com.workmade.helpdesk.HelpDesk.enums.ProfileEnum;
import br.com.workmade.helpdesk.HelpDesk.util.EntityHeaderUtil;


public class User extends EntityHeaderUtil {
	
	@Indexed(unique= true)
	@NotBlank(message= "Campo obrigatório")
	@Email(message= "O campode ter um email válido")
	private String email;
	
	@NotBlank(message= "senha obrigatória")
	@Size(min= 6)
	private String password;
	
	private ProfileEnum profile;

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

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}


}
