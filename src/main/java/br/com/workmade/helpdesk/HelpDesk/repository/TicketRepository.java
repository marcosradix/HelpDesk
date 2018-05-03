package br.com.workmade.helpdesk.HelpDesk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.workmade.helpdesk.HelpDesk.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String>{

	Page<Ticket> findByUserIdOrderByDateDesc(Pageable pages, String userId);
	
	Page<Ticket> findByTituloIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(
			String titulo, String status, String priority, Pageable pages);
	
	Page<Ticket> findByTituloIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(
			String titulo, String status, String priority, Pageable pages);
	
	Page<Ticket> findByTituloIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(
			String titulo, String status, String priority, Pageable pages);
	
	Page<Ticket> findByNumber(Integer number, Pageable pages);
}
