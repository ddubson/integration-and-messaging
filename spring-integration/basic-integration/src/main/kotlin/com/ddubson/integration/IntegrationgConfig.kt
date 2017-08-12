package com.ddubson.integration

import com.ddubson.CommandLineLogAdapter
import com.ddubson.LogAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.channel.MessageChannels

@Configuration
open class IntegrationgConfig {
    @Bean("inputChannel")
    open fun inputChannel(): DirectChannel {
        return MessageChannels.direct().get()
    }

    @Bean("outputChannel")
    open fun outputChannel(): DirectChannel {
        return MessageChannels.direct().get()
    }

    /**
     * Input Channel
     * -> Print Service (Service Activator)
     * -> Output Channel
     */
    @Bean
    open fun basicFlow(): IntegrationFlow {
        return IntegrationFlows
                .from(inputChannel())
                .handle(printService())
                .channel(outputChannel())
                .get()
    }

    @Bean
    open fun logAdapter(): LogAdapter {
        return CommandLineLogAdapter()
    }

    @Bean
    open fun printService(): PrintService {
        return PrintService(logAdapter())
    }
}