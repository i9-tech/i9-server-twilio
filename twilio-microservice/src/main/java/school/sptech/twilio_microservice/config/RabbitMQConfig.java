package school.sptech.twilio_microservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange topicExchange() {
        String exchangeName = "notificacoes.topic";
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue queue() {
        String queueName = "notificacoes.master.queue";
        return new Queue(queueName);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding javamailBinding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue)
                .to(topicExchange)
                .with("evento.twilio.#");
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