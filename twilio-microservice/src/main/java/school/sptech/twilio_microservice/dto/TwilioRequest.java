package school.sptech.twilio_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Descrição: DTO (Data Transfer Object) usado para empacotar (produtor) e desempacotar (consumidor)
// os dados da mensagem que transitam na fila do RabbitMQ.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwilioRequest implements Serializable {
    private String to;      // Descrição: Número de telefone do destinatário (ex: +5511999998888)
    private String message; // Descrição: Corpo da mensagem a ser enviada
}