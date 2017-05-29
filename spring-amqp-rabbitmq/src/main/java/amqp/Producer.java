package amqp;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class Producer implements CommandLineRunner {
    private final RabbitMessagingTemplate messagingTemplate;

    public Producer(RabbitMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Notification notification = new Notification(UUID.randomUUID().toString(), "Hello World!", LocalDate.now());
        Map<String, Object> headers = new HashMap<>();
        headers.put("notification-id", notification.getId());

        this.messagingTemplate.convertAndSend(Application.NOTIFICATIONS, notification, headers,
                message -> {
                    System.out.println("Sending " + message.getPayload().toString());
                    return message;
                });
    }
}
