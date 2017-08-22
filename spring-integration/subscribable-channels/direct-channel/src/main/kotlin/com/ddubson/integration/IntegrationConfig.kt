package com.ddubson.integration

import com.ddubson.CommandLineLogAdapter
import com.ddubson.LogAdapter
import com.ddubson.integration.services.PrintService
import com.ddubson.integration.services.UppercasePrintService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.PublishSubscribeSpec
import org.springframework.integration.dsl.channel.MessageChannels
import org.springframework.integration.dsl.support.Consumer
import org.springframework.messaging.MessageChannel
import java.util.concurrent.Executors

@Configuration
class IntegrationConfig {
    @Bean
    fun integrationFlow(): IntegrationFlow {
        val consumer1 = IntegrationFlow { f ->
            f.handle(printService(), "print", { c -> c.order(1) })
        }

        val consumer2 = IntegrationFlow { f ->
            f.handle(uppercasePrintService(), "print", { c -> c.order(2) })
        }

        return IntegrationFlows.from("inputChannel")
                .publishSubscribeChannel(Executors.newCachedThreadPool(),
                        Consumer<PublishSubscribeSpec> { s ->
                            s.subscribe(consumer1).subscribe(consumer2)
                        }).get()
    }

    @Bean
    fun inputChannel(): MessageChannel = MessageChannels.direct("inputChannel").get()

    @Bean
    fun printService(): PrintService = PrintService(logAdapter())

    @Bean
    fun uppercasePrintService(): UppercasePrintService = UppercasePrintService(logAdapter())

    @Bean
    fun logAdapter(): LogAdapter = CommandLineLogAdapter()
}