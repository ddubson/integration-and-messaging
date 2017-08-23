package com.ddubson.integration

import com.ddubson.CommandLineLogAdapter
import com.ddubson.LogAdapter
import com.ddubson.integration.interceptors.CustomChannelInterceptor
import com.ddubson.integration.services.PrintService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class IntegrationConfig {
    @Bean
    fun logAdapter(): LogAdapter = CommandLineLogAdapter()

    @Bean
    fun printService(): PrintService = PrintService(logAdapter())

    @Bean
    fun customChannelInterceptor(): CustomChannelInterceptor = CustomChannelInterceptor(logAdapter())
}