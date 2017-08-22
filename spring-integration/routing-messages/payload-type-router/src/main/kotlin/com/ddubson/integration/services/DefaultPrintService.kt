package com.ddubson.integration.services

import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component
class DefaultPrintService : PrintService {
    override fun print(msg: Message<*>) {
        println("[${Thread.currentThread().name}] Default service: ${msg.payload}")
    }
}