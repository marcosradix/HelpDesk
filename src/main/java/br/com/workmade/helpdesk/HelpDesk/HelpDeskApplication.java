package br.com.workmade.helpdesk.HelpDesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.workmade.helpdesk.HelpDesk.entity.User;
import br.com.workmade.helpdesk.HelpDesk.enums.ProfileEnum;
import br.com.workmade.helpdesk.HelpDesk.repository.UserRepository;

@SpringBootApplication
public class HelpDeskApplication extends SpringBootServletInitializer {
//usar jboss EAP 7.+ ou wildfly
	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
		
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(getClass());
	    }
	 @Bean
	 CommandLineRunner init(UserRepository userRepository, PasswordEncoder passworEncoder){
		return args ->{
			initUsers(userRepository, passworEncoder);
		};
		 
	 }
	 
	 private void initUsers(UserRepository userRepository, PasswordEncoder passworEncoder){
		 User user = new User();
		 user.setEmail("admin@workmade.com");
		 user.setPassword(passworEncoder.encode("workmade"));
		 user.setProfile(ProfileEnum.ROLE_ADMIN);
		 
		 User find  = userRepository.findByEmail("admin@workmade.com");
		 if(find == null){
			 userRepository.save(user);
		 }
		 
	 }
}
