package br.com.workmade.helpdesk.HelpDesk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.workmade.helpdesk.HelpDesk.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
}