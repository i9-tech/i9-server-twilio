package school.sptech.twilio_microservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import school.sptech.twilio_microservice.config.RabbitMQConfig;
import school.sptech.twilio_microservice.service.TwilioService;
import school.sptech.twilio_microservice.dto.TwilioRequest;

@Component
public class TwilioMessageListener {

    private final TwilioService twilioService;

    public TwilioMessageListener(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    @RabbitListener(queues = "notificacoes.master.queue")
    public void consumeMessage(TwilioRequest request) {
        twilioService.sendWhatsappMessage(request);

        System.out.println("Mensagem consumida da fila e enviada para o TwilioService.");
    }
}