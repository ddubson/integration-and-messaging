package com.ddubson.integration

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.integration.support.MessageBuilder
import org.springframework.messaging.Message

@SpringBootApplication
class Application(private val logAdapter: LogAdapter,
                  private val gateway: PrinterGateway) : ApplicationRunner {
    private val numberOfMessages = 10

    override fun run(args: ApplicationArguments?) {
        logAdapter.info("[1] Subscribable Round-Robin Direct Channel", ANSIColor.ANSI_WHITE)
        (0..numberOfMessages).forEach { i ->
            val message: Message<String> = MessageBuilder.withPayload("Message $i")
                    .build()
            logAdapter.info("[1] Sending message $i to input channel.", ANSIColor.ANSI_RED)
            this.gateway.print(message)
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}