package com.ddubson.integration

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component("printService")
class PrintService(private val lg: LogAdapter) {
    fun print(msg: Message<String>) {
        lg.info("[2.2] [${Thread.currentThread().name}] ${msg.payload}", ANSIColor.ANSI_CYAN)
    }
}