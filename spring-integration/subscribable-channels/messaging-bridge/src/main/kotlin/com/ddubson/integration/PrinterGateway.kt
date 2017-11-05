package com.ddubson.integration

import org.springframework.integration.annotation.Gateway
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.messaging.Message
import java.util.concurrent.Future

@MessagingGateway
interface PrinterGateway {
    @Gateway(requestChannel = "bridgedPollableChannel")
    fun print(message: Message<*>): Future<Message<*>>
}