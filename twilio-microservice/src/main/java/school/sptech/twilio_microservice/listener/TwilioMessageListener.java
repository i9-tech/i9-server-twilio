package school.sptech.twilio_microservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import school.sptech.twilio_microservice.config.RabbitMQConfig;
import school.sptech.twilio_microservice.service.TwilioService;
import school.sptech.twilio_microservice.dto.TwilioRequest;

@Component
public class TwilioMessageListener {

    private final TwilioService twilioService;

    // Descrição: Construtor para injeção de dependência do serviço Twilio.
    public TwilioMessageListener(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    /**
     * Descrição: Este método escuta a fila 'twilio-queue'. O Spring AMQP automaticamente
     * converte a mensagem JSON de volta para um objeto TwilioRequest.
     * @param request O objeto TwilioRequest recebido da fila.
     */
    @RabbitListener(queues = RabbitMQConfig.TWILIO_QUEUE)
    public void consumeMessage(TwilioRequest request) {
        // Ajuste no nome do método chamado
        twilioService.sendWhatsappMessage(request);

        System.out.println("Mensagem consumida da fila e enviada para o TwilioService.");
    }
}