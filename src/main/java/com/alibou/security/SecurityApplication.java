package com.alibou.security;

import com.alibou.security.auth.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import com.alibou.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.alibou.security.user.Role.*;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {

        try {
            SpringApplication.run(SecurityApplication.class, args);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }

	//@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("123")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("manager")
					.lastname("manager")
					.email("manager@mail.com")
					.password("123")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

			var callcenter = RegisterRequest.builder()
					.firstname("callcenter")
					.lastname("callcenter")
					.email("callcenter@mail.com")
					.password("123")
					.role(CALLCENTER)
					.build();
			System.out.println("Callcenter token: " + service.register(callcenter).getAccessToken());

		};
	}
}
