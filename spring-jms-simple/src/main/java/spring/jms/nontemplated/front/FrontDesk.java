package spring.jms.nontemplated.front;


import org.springframework.stereotype.Component;
import spring.jms.shared.Mail;

@Component
@FunctionalInterface
public interface FrontDesk {
    void sendMail(Mail mail);
}
