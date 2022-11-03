package com.fadryl.media.logger.impl

import android.util.Log
import com.fadryl.media.loggerinterface.ILogAdapter
import com.fadryl.media.loggerinterface.LogLevel

/**
 * Created by Hoi Lung Lam (FaDr_YL) on 2022/10/26
 */
class PrettyLogcatLogAdapter: ILogAdapter {
    companion object {
        private const val header = "╒═════════════════════════════════════════════════════════════════════════════════════════"
        private const val prefix = "│"
        private const val midSep = "├╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌"
        private const val footer = "╘═════════════════════════════════════════════════════════════════════════════════════════"
    }

    override fun isLoggable(level: Int): Boolean {
        return LogLevel.VERBOSE <= level && level <= LogLevel.ASSERT
    }

    override fun log(level: Int, tag: String, message: String?, throwable: Throwable?) {
        when (level) {
            LogLevel.VERBOSE -> logAux(tag, message, throwable, Log::v)
            LogLevel.DEBUG -> logAux(tag, message, throwable, Log::d)
            LogLevel.INFO -> logAux(tag, message, throwable, Log::i)
            LogLevel.WARN -> logAux(tag, message, throwable, Log::w)
            LogLevel.ERROR -> logAux(tag, message, throwable, Log::e)
            LogLevel.ASSERT -> logAux(tag, message, throwable, Log::wtf)
        }
    }

    private fun String.lines(): List<String> = split("\n")

    private fun logAux(tag: String, message: String?, throwable: Throwable?, logFunc: (String, String) -> Int) {
        if (message == null && throwable == null) return

        logFunc(tag, header)
        message?.lines()?.let {
            for (line in it) {
                logFunc(tag, "$prefix $line")
            }
        }
        if (message != null && throwable != null) logFunc(tag, midSep)
        throwable?.let {
            it.message?.let { msg ->
                logFunc(tag, "$prefix $msg")
            }
            for (trace in it.stackTrace) {
                logFunc(tag, "$prefix $trace")
            }
        }
        logFunc(tag, footer)
    }
}