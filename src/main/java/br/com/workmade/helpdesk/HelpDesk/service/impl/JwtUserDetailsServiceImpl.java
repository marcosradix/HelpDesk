package br.com.workmade.helpdesk.HelpDesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.workmade.helpdesk.HelpDesk.entity.User;
import br.com.workmade.helpdesk.HelpDesk.security.jwt.JwtUserFactory;
import br.com.workmade.helpdesk.HelpDesk.service.UserService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);
		if(user == null){
			throw new UsernameNotFoundException(String.format("Usuário não encontrado para o usuário '%s' informado.", email));
		}else{
			return JwtUserFactory.create(user);
		}
		
	}

	
}
