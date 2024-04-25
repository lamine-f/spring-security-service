package sn.lord.ensae.hint.security;

import sn.lord.ensae.hint.security.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Security  {

	public static void main(String[] args) {
		SpringApplication.run(Security.class, args);
	}

}
