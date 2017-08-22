package com.ddubson.integration.services

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter
import org.springframework.messaging.Message

class PrintService(private val logAdapter: LogAdapter) {
    fun print(msg: Message<String>) {
        logAdapter.info("[2] Consuming input channel -- [${Thread.currentThread().name}] ${msg.payload}", ANSIColor.ANSI_GREEN)
    }
}
