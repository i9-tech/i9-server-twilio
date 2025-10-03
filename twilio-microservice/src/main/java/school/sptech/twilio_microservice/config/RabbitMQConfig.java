package school.sptech.twilio_microservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // Descrição: Nome da fila que o monolito de produtos enviará as mensagens para o Twilio consumir.
    public static final String TWILIO_QUEUE = "twilio-queue";

    @Bean
    public Queue twilioQueue() {
        // Descrição: Cria a fila no RabbitMQ. 'true' indica que a fila é durável (sobrevive a reinicializações).
        return new Queue(TWILIO_QUEUE, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        // Descrição: Define o conversor de mensagens, que transforma o objeto Java (TwilioRequest) em JSON
        // antes de enviar, e de volta para objeto Java ao receber.
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        // Descrição: Cria a Factory para o Listener, configurando a conexão e o conversor JSON.
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}