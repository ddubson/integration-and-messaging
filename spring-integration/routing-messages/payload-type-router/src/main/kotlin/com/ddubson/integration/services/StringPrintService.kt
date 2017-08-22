package com.ddubson.integration.services

import com.ddubson.ANSIColor.ANSI_GREEN
import com.ddubson.LogAdapter
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component("stringPrintService")
class StringPrintService(private val logAdapter: LogAdapter): PrintService {
    override fun print(msg: Message<*>) {
        logAdapter.info("[2] String service: [${Thread.currentThread().name}] ${msg.payload}", ANSI_GREEN)
    }
}