package amqp;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.core.MessagePostProcessor;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static final String NOTIFICATIONS = "notifications";

    @Autowired
    Producer producer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Notification notification = new Notification(UUID.randomUUID().toString(), "Hello World!", LocalDate.now());
        producer.send(notification);
    }

    @Bean
    public InitializingBean initQueues(AmqpAdmin amqpAdmin) {
        return () -> {
            Queue queue = new Queue(NOTIFICATIONS, true);
            DirectExchange exchange = new DirectExchange(NOTIFICATIONS);
            Binding binding = BindingBuilder.bind(queue).to(exchange).with(NOTIFICATIONS);
            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareBinding(binding);
        };
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }

    @Bean
    public MessagePostProcessor messagePostProcessor() {
        return (message) -> {
            System.out.println("Sending " + message.getPayload());
            return message;
        };
    }
}
