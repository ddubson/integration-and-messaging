package spring.jms.templated;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.JmsException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.MessageListener;

import static org.junit.Assert.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class ActiveMqApplicationTest {
    @Autowired
    Sender activemqSender;

    @Test
    public void springJmsSendReceiveTest() {
        try {
            activemqSender.send("Hello World!");
        } catch(JmsException e) {
            fail("You must have ActiveMQ broker running...");
        }
    }
}