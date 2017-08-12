package com.ddubson.integration

import com.ddubson.ANSIColor.*
import com.ddubson.LogAdapter
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder

class PrintService(val logAdapter: LogAdapter) {
    fun print(msg: Message<String>): Message<Any?>? {
        msg.headers.forEach { (k, v) ->
            logAdapter.info("[2] Header: $k:$v", ANSI_GREEN)
        }

        logAdapter.info("[2] Payload: ${msg.payload}", ANSI_GREEN)
        return MessageBuilder.withPayload(msg.headers["id"]).build()
    }
}