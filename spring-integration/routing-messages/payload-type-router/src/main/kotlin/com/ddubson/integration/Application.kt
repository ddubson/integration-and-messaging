package com.ddubson.integration

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.messaging.support.MessageBuilder

@SpringBootApplication
class Application(val logAdapter: LogAdapter,
                  val gateway: PrinterGateway) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        logAdapter.info("[1] Send messages to gateway.", ANSIColor.ANSI_WHITE)

        (0..10).forEach { i ->
            val message = MessageBuilder.withPayload("Message " + i).build()
            logAdapter.info("[1.1] Send string message ${message.payload}", ANSIColor.ANSI_YELLOW)
            gateway.print(message)
        }

        (0..10).forEach { i ->
            val message = MessageBuilder.withPayload(i).build()
            logAdapter.info("[1.2] Send int message: ${message.payload}", ANSIColor.ANSI_PURPLE)
            gateway.print(message)
        }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
