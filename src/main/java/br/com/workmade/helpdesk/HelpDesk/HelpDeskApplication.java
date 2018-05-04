package br.com.workmade.helpdesk.HelpDesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

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
	/* @Bean
	 CommandLineRunner init(UserRepository userRepository, PasswordEncoder passworEncoder){
		return args ->{
			initUsers(userRepository, passworEncoder);
		};
		 
	 }
	 
	 private void initUsers(UserRepository userRepository, PasswordEncoder passworEncoder){
		 User user = new User();
		 user.setEmail("admin@workmade.com");
		 user.setPassword(passworEncoder.encode("workmade"));
		 user.setProfile(ProfileEnum.ADMIN);
		 
		 User find  = userRepository.findByEmail("admin@workmade.com");
		 if(find == null){
			 userRepository.save(user);
		 }
		 
	 }*/
}
