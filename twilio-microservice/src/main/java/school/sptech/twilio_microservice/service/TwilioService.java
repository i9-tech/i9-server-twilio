package school.sptech.twilio_microservice.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import school.sptech.twilio_microservice.config.TwilioConfig;
import school.sptech.twilio_microservice.dto.TwilioRequest;

@Service
public class TwilioService {
    private final TwilioConfig twilioConfig;

    // Descrição: Construtor para injeção de dependência da configuração do Twilio.
    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    /**
     * Descrição: Envia uma mensagem de WhatsApp usando a API Twilio, formatando os números com o prefixo 'whatsapp:'.
     * @param request Objeto contendo o número de destino ('to') e o corpo da mensagem ('message').
     */
    public void sendWhatsappMessage(TwilioRequest request) { // Nome do método alterado para 'sendWhatsappMessage'

        // CORREÇÃO: Usa o número de destino do DTO
        String toNumber = "whatsapp:" + request.getTo();

        // Descrição: Cria a mensagem Twilio. O primeiro PhoneNumber é o destino, o segundo é a origem (Twilio).
        Message.creator(
                new PhoneNumber(toNumber), // Destino (do DTO)
                new PhoneNumber("whatsapp:" + twilioConfig.getPhoneNumber()), // Origem (config Twilio)
                request.getMessage()
        ).create();

        System.out.println("Mensagem enviada para: " + request.getTo());
    }
}