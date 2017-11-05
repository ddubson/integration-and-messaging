package com.ddubson.integration

import com.ddubson.ANSIColor
import com.ddubson.CommandLineLogAdapter
import com.ddubson.LogAdapter
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource
import org.springframework.messaging.support.MessageBuilder

@SpringBootApplication
@ImportResource("integration-context.xml")
class Application(private val gateway: PrinterGateway) : ApplicationRunner{
    private val numberOfMessages: Int = 10

    override fun run(args: ApplicationArguments?) {
        logAdapter().info("[1] Messaging Bridge", ANSIColor.ANSI_WHITE)

        (0..numberOfMessages).forEach {
            val message = MessageBuilder.withPayload("Message $it").build()
            gateway.print(message)
        }
    }

    @Bean
    fun logAdapter(): LogAdapter = CommandLineLogAdapter()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}