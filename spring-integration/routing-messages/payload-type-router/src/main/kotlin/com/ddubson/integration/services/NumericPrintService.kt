package com.ddubson.integration.services

import com.ddubson.ANSIColor.ANSI_CYAN
import com.ddubson.LogAdapter
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component("numericPrintService")
class NumericPrintService(private val logAdapter: LogAdapter): PrintService {
    override fun print(msg: Message<*>) {
        logAdapter.info("[2] Int service: [${Thread.currentThread().name}] ${msg.payload}", ANSI_CYAN)
    }
}