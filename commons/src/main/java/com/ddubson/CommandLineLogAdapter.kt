package com.ddubson

class CommandLineLogAdapter : LogAdapter {
    override fun info(message: String) {
        println(message)
    }

    override fun info(message: String, color: ANSIColor) {
        String.format("%s %s %s\n", color.color(), message, ANSIColor.ANSI_RESET.color())
    }

}