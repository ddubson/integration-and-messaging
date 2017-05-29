package amqp;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Producer {
    private final RabbitMessagingTemplate messagingTemplate;
    private final MessagePostProcessor messagePostProcessor;

    public Producer(RabbitMessagingTemplate messagingTemplate, MessagePostProcessor messagePostProcessor) {
        this.messagingTemplate = messagingTemplate;
        this.messagePostProcessor = messagePostProcessor;
    }

    public void send(Notification notification) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("notification-id", notification.getId());

        this.messagingTemplate.convertAndSend(Application.NOTIFICATIONS, notification, headers,
                messagePostProcessor);
    }
}
