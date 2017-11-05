package com.ddubson.integration

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.messaging.support.MessageBuilder

@SpringBootApplication
@ImportResource("integration-context.xml")
class Application(val logAdapter: LogAdapter,
                  val gateway: PrinterGateway) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        logAdapter.info("[1] Subscribable Round-Robin Direct Channel - Channel Interceptor", ANSIColor.ANSI_WHITE)
        (0..10).forEach { i ->
            val message = MessageBuilder.withPayload("Message $i").build()
            logAdapter.info("[1] Sending message $i to input channel.", ANSIColor.ANSI_YELLOW)
            gateway.print(message)
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}