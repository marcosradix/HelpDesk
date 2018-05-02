package br.com.workmade.helpdesk.HelpDesk.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.workmade.helpdesk.HelpDesk.entity.User;
import br.com.workmade.helpdesk.HelpDesk.enums.ProfileEnum;

public class JwtUserFactory {

	private JwtUserFactory(){}
	
	
	public static JwtUserUtil create(User user){
		return new JwtUserUtil(user.getId(), user.getEmail(), user.getPassword(),
				mapToGrantedAthorities(user.getProfile()));
	}


	private static List<GrantedAuthority> mapToGrantedAthorities(ProfileEnum profileEnum) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(profileEnum.toString()));
		return authorities;
	}
	
}
