package com.ddubson.integration

import com.ddubson.ANSIColor
import com.ddubson.LogAdapter
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

@Component("filePrinter")
class FilePrinter(val logAdapter: LogAdapter) {
    fun print(file: File) {
        BufferedReader(FileReader(file)).use { reader ->
            reader.lines().forEach { line ->
                logAdapter.info("[2][file: ${file.name}] $line", ANSIColor.ANSI_GREEN)
            }
        }
    }
}