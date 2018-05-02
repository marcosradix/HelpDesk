package br.com.workmade.helpdesk.HelpDesk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.workmade.helpdesk.HelpDesk.entity.ChangeStatus;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String>{
	
	Iterable<ChangeStatus> findByTicketIdOrderByDateChangeStatusDesc(String ticketID);
 
}
