package br.com.workmade.helpdesk.HelpDesk.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.workmade.helpdesk.HelpDesk.entity.User;

public interface UserService {
	
	User findByEmail(String email);
	
	User CreateOrUpdate(User user);
	
	User findById(String id);
	
	List<User> findAllUsers();
	
	void delete(String id);
	
	Page<User> findAll(int page, int count);

}
