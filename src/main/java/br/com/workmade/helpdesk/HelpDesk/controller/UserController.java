package br.com.workmade.helpdesk.HelpDesk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import br.com.workmade.helpdesk.HelpDesk.entity.User;
import br.com.workmade.helpdesk.HelpDesk.response.Response;
import br.com.workmade.helpdesk.HelpDesk.service.UserService;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> create(HttpServletRequest request, @RequestBody User user,
			BindingResult result){
		Response<User> response = new Response<>();
		
		try{
			validateCreateUser(user, result);
			if(result.hasErrors()){
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = (User) userService.CreateOrUpdate(user);
			response.setData(userPersisted);
		}catch(DuplicateKeyException dE){
			response.getErros().add("E-mail já resgistrado.");
			return ResponseEntity.badRequest().body(response);
		}catch(Exception e){
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
		
	}
	
	private void validateCreateUser(User user, BindingResult result){
		if(user.getEmail() == null){
			result.addError(new ObjectError("User", "Email não informado."));
		}
	}
	
	private void validateUpdateUser(User user, BindingResult result){
		if(user.getId() == null){
			result.addError(new ObjectError("User", "Id não informado."));
		}
		if(user.getEmail() == null){
			result.addError(new ObjectError("User", "Email não informado."));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> update(HttpServletRequest request, @RequestBody User user,
			BindingResult result){
		Response<User> response = new Response<>();
		try{
			validateUpdateUser(user, result);
			if(result.hasErrors()){
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = (User) userService.CreateOrUpdate(user);
			response.setData(userPersisted);
			
		}catch(Exception e){
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value="{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> findById(@PathVariable("id") String id){
		Response<User> response = new Response<>();
		User user = userService.findById(id);
		if(user == null){
			response.getErros().add(String.format("Registro não encontrado = %s", id));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(user);
		return ResponseEntity.ok(response);
		
	}
	
	
	@DeleteMapping(value="{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<>();
		User user = userService.findById(id);
		if(user == null){
			response.getErros().add(String.format("Registro não encontrado = %s", id));
			return ResponseEntity.badRequest().body(response);
		}
		userService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value= "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<User>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<User>> response = new Response<>();
		Page<User> users = userService.findAll(page, count);
		response.setData(users);
		return ResponseEntity.ok(response);
		
	}

	
}






