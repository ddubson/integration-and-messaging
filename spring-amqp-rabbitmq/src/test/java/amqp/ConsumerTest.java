package amqp;

import com.ddubson.LogAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerTest {
    @Mock
    private LogAdapter logAdapter;

    @InjectMocks
    private Consumer consumer;

    private LocalDate today = LocalDate.now();

    @Test
    public void onNotification_LogsMessageAndHeadersThatWereReceived() {
        String expectedMessage = "[Message: Hello World!]";

        Notification notification = new Notification(UUID.randomUUID().toString(), expectedMessage, today);
        Message<Notification> message = new GenericMessage<>(notification);

        consumer.onNotification(message);

        verify(logAdapter).info(String.format("[Headers: %s]", message.getHeaders()));
        verify(logAdapter).info(expectedMessage);
    }
}