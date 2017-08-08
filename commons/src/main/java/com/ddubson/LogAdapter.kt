package com.ddubson

interface LogAdapter {
    fun info(message: String)

    fun info(message: String, color: ANSIColor)
}