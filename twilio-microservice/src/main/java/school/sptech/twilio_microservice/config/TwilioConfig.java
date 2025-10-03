package school.sptech.twilio_microservice.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// Descrição: Classe de configuração que carrega as credenciais do Twilio (accountSid, authToken, phoneNumber)
// do arquivo application.properties/yml, usando o prefixo "twilio".
@Configuration
@ConfigurationProperties(prefix = "twilio")
@Getter
@Setter
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String phoneNumber; // número Twilio (origem)

    // Descrição: Método que inicializa o cliente Twilio imediatamente após a construção do bean,
    // garantindo que as credenciais sejam aplicadas antes do uso.
    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }
}