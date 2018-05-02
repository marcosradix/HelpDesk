package br.com.workmade.helpdesk.HelpDesk.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;

import br.com.workmade.helpdesk.HelpDesk.enums.PriorityEnum;
import br.com.workmade.helpdesk.HelpDesk.enums.StatusEnum;
import br.com.workmade.helpdesk.HelpDesk.util.EntityHeaderUtil;

public class Ticket extends EntityHeaderUtil{
	
	@DBRef(lazy= true)
	private User user;

	private LocalDate date; 
	
	private String titulo;
	
	private Integer number;
	
	private StatusEnum status;
	
	private PriorityEnum priority;
	
	@DBRef(lazy= true)
	private User assignedUser;
	
	private String description;
	
	private String image;
	
	@Transient
	private List<ChangeStatus> changes;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ChangeStatus> getChanges() {
		return changes;
	}

	public void setChanges(List<ChangeStatus> changes) {
		this.changes = changes;
	}
	
	
}







