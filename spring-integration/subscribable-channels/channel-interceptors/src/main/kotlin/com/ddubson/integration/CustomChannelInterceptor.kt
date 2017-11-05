package com.ddubson.integration;

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageBuilder;

class CustomChannelInterceptor(private val logAdapter: LogAdapter) : ChannelInterceptorAdapter() {
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*> {
        logAdapter.info("[2] ${message.payload} intercepted. Putting back to input channel.", ANSIColor.ANSI_GREEN)
        val msg = MessageBuilder.withPayload("${message.payload} [INTERCEPTED]").build()
        return super.preSend(msg, channel)
    }
}
