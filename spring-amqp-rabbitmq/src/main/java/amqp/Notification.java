package amqp;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by ddubs on 12/1/2016.
 */
public class Notification implements Serializable {
    private String id;
    private String message;
    private LocalDate dateTime;

    public Notification(String id, String message, LocalDate dateTime) {
        this.id = id;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

}
