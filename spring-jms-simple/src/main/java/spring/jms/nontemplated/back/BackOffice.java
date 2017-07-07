package spring.jms.nontemplated.back;

import spring.jms.shared.Mail;

@FunctionalInterface
public interface BackOffice {
	Mail receiveMail();
}
