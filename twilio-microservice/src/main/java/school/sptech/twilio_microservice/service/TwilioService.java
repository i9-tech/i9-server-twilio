package school.sptech.twilio_microservice.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import school.sptech.twilio_microservice.config.TwilioConfig;
import school.sptech.twilio_microservice.dto.TwilioRequest;

@Service
public class TwilioService {
    private final TwilioConfig twilioConfig;

    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendWhatsappMessage(TwilioRequest request) {

        String toNumber = "whatsapp:" + request.getTo();

        Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber("whatsapp:" + twilioConfig.getPhoneNumber()),
                request.getMessage()
        ).create();

        System.out.println("Mensagem enviada para: " + request.getTo());
    }
}