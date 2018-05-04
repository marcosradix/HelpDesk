package br.com.workmade.helpdesk.HelpDesk.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.workmade.helpdesk.HelpDesk.security.jwt.JwtTokenUtil;
import br.com.workmade.helpdesk.HelpDesk.service.TicketService;
import br.com.workmade.helpdesk.HelpDesk.service.UserService;
import br.com.workmade.helpdesk.HelpDesk.entity.ChangeStatus;
import br.com.workmade.helpdesk.HelpDesk.entity.Ticket;
import br.com.workmade.helpdesk.HelpDesk.entity.User;
import br.com.workmade.helpdesk.HelpDesk.enums.StatusEnum;
import br.com.workmade.helpdesk.HelpDesk.response.Response;


@RestController
@RequestMapping(value="/api/ticket")
@CrossOrigin(origins="*")
public class TicketController {

	@Autowired
	private TicketService ticketService; 
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('CLIENT')")
	public ResponseEntity<Response<Ticket>> createORUpdate(HttpServletRequest request, @RequestBody Ticket ticket,
			BindingResult result){
		Response<Ticket> response = new Response<>();
		try {
			validateCreateTicket(ticket, result);
			if(result.hasErrors()){
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			ticket.setStatus(StatusEnum.getStatus("New"));
			ticket.setUser(userFormRequest(request));
			ticket.setDate(new Date());
			ticket.setNumber(generateNumber());
			Ticket ticketPersisted = (Ticket) ticketService.createORUpdate(ticket);
			response.setData(ticketPersisted);
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('CLIENT')")
	public ResponseEntity<Response<Ticket>> update(HttpServletRequest request, @RequestBody Ticket ticket,
			BindingResult result){
		Response<Ticket> response = new Response<>();
		try {
			validateUpdateTicket(ticket, result);
			if(result.hasErrors()){
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Ticket ticketCurrent = ticketService.findById(ticket.getId());
			ticket.setStatus(ticketCurrent.getStatus());
			ticket.setUser(ticketCurrent.getUser());
			ticket.setDate(ticketCurrent.getDate());
			ticket.setNumber(ticketCurrent.getNumber());
			if(ticketCurrent.getAssignedUser() != null){
				ticket.setAssignedUser(ticketCurrent.getAssignedUser());
			}
			Ticket ticketPersisted = (Ticket) ticketService.createORUpdate(ticket);
			response.setData(ticketPersisted);
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAnyRole('CLIENT','TECHNICIAN')")
	public ResponseEntity<Response<Ticket>> findById(@PathVariable("id") String id){
		Response<Ticket> response = new Response<>();
		Ticket ticket = ticketService.findById(id);
		if(ticket == null){
			response.getErros().add(String.format("Registro não encontrado com id: %s", id));
			return ResponseEntity.badRequest().body(response);
		}
		
		List<ChangeStatus> changes = new ArrayList<>();
		Iterable<ChangeStatus> chagesCurrent = ticketService.listChangeStatus(ticket.getId());
		
		for (Iterator<ChangeStatus> iterator = chagesCurrent.iterator(); iterator.hasNext();) {
			ChangeStatus changeStatus = (ChangeStatus) iterator.next();
			changeStatus.setTicket(null);
			changes.add(changeStatus);
		}
		ticket.setChanges(changes);
		response.setData(ticket);
		return ResponseEntity.ok(response);
	
	}
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('CLIENT')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<>();
		Ticket ticket = ticketService.findById(id);
		if(ticket == null){
			response.getErros().add(String.format("Registro não encontrado com id: %s", id));
			return ResponseEntity.badRequest().body(response);
		}
		ticketService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	private void validateUpdateTicket(Ticket ticket, BindingResult result) {
		if(ticket.getId() == null){
			result.addError(new ObjectError("Ticket", "Id não informado."));
			return;
		}
		if(ticket.getTitulo() == null){
			result.addError(new ObjectError("Ticket", "Título não informado."));
			return;
		}
		
	}

	private Integer generateNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}


	private User userFormRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String email = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByEmail(email);
	}


	private void validateCreateTicket(Ticket ticket, BindingResult result){
		if(ticket.getTitulo() == null){
			result.addError(new ObjectError("Ticket", "Título não informado."));
			return;
		}
		
	}
	
}




