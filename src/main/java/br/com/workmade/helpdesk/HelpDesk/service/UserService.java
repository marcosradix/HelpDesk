package br.com.workmade.helpdesk.HelpDesk.service;

import org.springframework.data.domain.Page;

import br.com.workmade.helpdesk.HelpDesk.entity.User;

public interface UserService {
	
	User findByEmail(String email);
	
	User CreateOrUpdate(User user);
	
	User findById(String id);
	
	void delete(String id);
	
	Page<User> findAll(int page, int count);

}
