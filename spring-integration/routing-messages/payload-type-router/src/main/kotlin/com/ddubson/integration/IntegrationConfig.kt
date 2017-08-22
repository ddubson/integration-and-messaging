package com.ddubson.integration

import com.ddubson.CommandLineLogAdapter
import com.ddubson.LogAdapter
import com.ddubson.integration.services.NumericPrintService
import com.ddubson.integration.services.PrintService
import com.ddubson.integration.services.StringPrintService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.messaging.MessageChannel

@Configuration
class IntegrationConfig {
    @Bean
    fun integrationFlow(): IntegrationFlow {
        return IntegrationFlows.from("inputChannel")
                .route<Any, String>({ i ->
                    when (i) {
                        is Int -> "int"
                        is String -> "string"
                        else -> "def"
                    }
                }, { i ->
                    i.channelMapping("int", "intChannel")
                            .channelMapping("string", "stringChannel")
                }).get()
    }

    @Bean
    @ServiceActivator(inputChannel = "intChannel")
    fun numericPrintService(): PrintService = NumericPrintService(logAdapter())

    @Bean
    @ServiceActivator(inputChannel = "stringChannel")
    fun stringPrintService(): PrintService = StringPrintService(logAdapter())

    @Bean
    fun inputChannel(): MessageChannel = DirectChannel()

    @Bean
    fun stringChannel(): MessageChannel = DirectChannel()

    @Bean
    fun intChannel(): MessageChannel = DirectChannel()

    @Bean
    fun logAdapter(): LogAdapter = CommandLineLogAdapter()
}