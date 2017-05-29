package spring.jms.nontemplated.back;

import spring.jms.Mail;

@FunctionalInterface
public interface BackOffice {
	Mail receiveMail();
}
