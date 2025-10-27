package school.sptech.twilio_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwilioMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwilioMicroserviceApplication.class, args);
	}

}
