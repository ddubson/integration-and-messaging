package com.ddubson

import com.ddubson.ANSIColor.ANSI_RESET

class CommandLineLogAdapter : LogAdapter {
    override fun info(message: String) {
        println(message)
    }

    override fun info(message: String, color: ANSIColor) {
        println("${color.color()} $message ${ANSI_RESET.color()}")
    }

}