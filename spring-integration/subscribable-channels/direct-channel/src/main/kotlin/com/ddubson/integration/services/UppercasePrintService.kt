package com.ddubson.integration.services;

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter
import org.springframework.messaging.Message

class UppercasePrintService(private val logAdapter: LogAdapter) {
    fun print(msg: Message<String>) {
        logAdapter
                .info("[2] Consuming input channel -- [${Thread.currentThread().name}] ${msg.payload.toUpperCase()}",
                        ANSIColor.ANSI_PURPLE)
    }
}
