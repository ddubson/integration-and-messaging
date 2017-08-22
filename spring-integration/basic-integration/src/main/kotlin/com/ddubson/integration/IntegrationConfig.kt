package com.ddubson.integration

import com.ddubson.CommandLineLogAdapter
import com.ddubson.LogAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.channel.MessageChannels

@Configuration
class IntegrationConfig {
    /**
     * Input Channel
     * -> Print Service (Service Activator)
     * -> Output Channel
     */
    //@Bean
    /*fun basicFlow(): IntegrationFlow {
        return IntegrationFlows
                .from(inputChannel())
                .handle(printService())
                .channel(outputChannel())
                .get()
    }*/

    @Bean("inputChannel")
    fun inputChannel(): DirectChannel = MessageChannels.direct().get()

    @Bean("outputChannel")
    fun outputChannel(): DirectChannel = MessageChannels.direct().get()

    @Bean
    fun logAdapter(): LogAdapter = CommandLineLogAdapter()

    @Bean
    @ServiceActivator(inputChannel = "inputChannel", outputChannel = "outputChannel")
    fun printService(): PrintService = PrintService(logAdapter())
}