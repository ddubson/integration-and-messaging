package com.ddubson.integration.services

import org.springframework.messaging.Message

interface PrintService {
    fun print(msg: Message<*>)
}