package amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.messaging.core.MessagePostProcessor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static amqp.Application.NOTIFICATIONS;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProducerTest {
    @Mock
    RabbitMessagingTemplate messagingTemplate;

    @Mock
    private MessagePostProcessor postProcessor;

    @InjectMocks
    Producer producer;

    private LocalDate today = LocalDate.now();

    @Test
    public void producer_shouldSendMessageIntoQueue() {
        Notification notification = new Notification(UUID.randomUUID().toString(), "Hello World!", today);
        Map<String, Object> headers = new HashMap<>();
        headers.put("notification-id", notification.getId());

        producer.send(notification);

        verify(messagingTemplate).convertAndSend(NOTIFICATIONS, notification, headers, postProcessor);
    }
}