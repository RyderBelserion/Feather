package com.ryderbelserion.feather.files

import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

object GitUtils {

    fun runGitCommandCheckFail(dir: File?, command: Array<String?>): Boolean {
        return try {
            val pb = ProcessBuilder(*command)
            pb.redirectErrorStream(true)
            pb.directory(dir)

            val p = pb.start()
            val reader = BufferedReader(InputStreamReader(p.inputStream))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                println(line)
            }

            waitForExit(p)
            p.exitValue() != 0
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun waitForExit(p: Process) {
        val start = System.currentTimeMillis()

        while (p.isAlive) {
            if (start + 1000 * 60 < System.currentTimeMillis()) throw RuntimeException("The git process took too long! " + p.isAlive)

            runCatching {
                Thread.sleep(100)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}