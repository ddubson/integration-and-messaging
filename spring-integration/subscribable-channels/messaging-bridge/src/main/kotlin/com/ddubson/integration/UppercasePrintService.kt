package com.ddubson.integration

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component("uppercasePrintService")
class UppercasePrintService(private val lg: LogAdapter) {
    fun print(msg: Message<String>) {
        lg.info(
                "[2.1] [${Thread.currentThread().name}] ${msg.payload.toUpperCase()}"
                , ANSIColor.ANSI_CYAN)

    }
}