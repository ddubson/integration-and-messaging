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

@SpringBootApplication
@ImportResource("integration-context.xml")
class Application : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        logAdapter().info("[1] Reading *.txt files from 'assets/read' directory.", ANSIColor.ANSI_CYAN)
    }

    @Bean
    fun logAdapter(): LogAdapter = CommandLineLogAdapter()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}