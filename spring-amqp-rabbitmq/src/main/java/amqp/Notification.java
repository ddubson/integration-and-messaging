package amqp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Notification implements Serializable {
    private String id;
    private String message;
    private LocalDate dateTime;
}
