package br.com.workmade.helpdesk.HelpDesk.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.DBRef;

import br.com.workmade.helpdesk.HelpDesk.enums.StatusEnum;
import br.com.workmade.helpdesk.HelpDesk.util.EntityHeaderUtil;

public class ChangeStatus extends EntityHeaderUtil {
	
	@DBRef
	private Ticket ticket;
	
	@DBRef
	private User userChange;
	
	private LocalDate dateChangeStatus;
	
	private StatusEnum status;

	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getUserChange() {
		return userChange;
	}

	public void setUserChange(User userChange) {
		this.userChange = userChange;
	}

	public LocalDate getDateChangeStatus() {
		return dateChangeStatus;
	}

	public void setDateChangeStatus(LocalDate dateChangeStatus) {
		this.dateChangeStatus = dateChangeStatus;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	

}
