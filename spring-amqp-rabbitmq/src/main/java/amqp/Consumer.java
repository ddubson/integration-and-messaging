package amqp;

import com.ddubson.LogAdapter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final LogAdapter log;

    public Consumer(LogAdapter log) {
        this.log = log;
    }

    @RabbitListener(queues = Application.NOTIFICATIONS)
    public void onNotification(Message<Notification> notification) {
        log.info("[Headers: " + notification.getHeaders().toString()+"]");
        log.info(notification.getPayload().getMessage());
    }
}
