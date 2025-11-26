package demo.badgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BadgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BadgeServiceApplication.class, args);
	}

}
