package com.ddubson.integration

import org.springframework.messaging.Message
import java.util.concurrent.Future

interface PrinterGateway {
    fun print(message: Message<*>): Future<Message<*>>
}