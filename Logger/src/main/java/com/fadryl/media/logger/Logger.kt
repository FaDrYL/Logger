package com.fadryl.media.logger

import android.support.annotation.IntRange
import com.fadryl.media.loggerinterface.ILogAdapter
import com.fadryl.media.loggerinterface.ILoggerBase
import com.fadryl.media.loggerinterface.LogLevel
import com.fadryl.media.loggermanager.LoggerManager

object Logger: ILoggerBase {
    private val manager by lazy { LoggerManager.instance }

    fun addAdapter(adapter: ILogAdapter, @IntRange(from = 0, to = 4) priority: Int = 2) {
        manager.addAdapter(priority, adapter)
    }

    fun removeAdapter(adapter: ILogAdapter, priority: Int = 2) {
        manager.removeAdapter(priority, adapter)
    }

    fun removeAdapterFromAll(adapter: ILogAdapter) {
        manager.removeAdapterFromAll(adapter)
    }

    fun enable() {
        manager.isEnable = true
    }

    fun disable() {
        manager.isEnable = false
    }

    override fun log(level: Int, tag: String, message: String?, throwable: Throwable?) {
        manager.log(level, tag, message, throwable)
    }

    fun v(tag: String, message: String) {
        log(LogLevel.VERBOSE, tag, message)
    }

    fun d(tag: String, message: String) {
        log(LogLevel.DEBUG, tag, message)
    }

    fun i(tag: String, message: String) {
        log(LogLevel.INFO, tag, message)
    }

    fun w(tag: String, message: String) {
        log(LogLevel.WARN, tag, message)
    }

    fun e(tag: String, message: String) {
        log(LogLevel.ERROR, tag, message = message)
    }

    fun e(tag: String, throwable: Throwable) {
        log(LogLevel.ERROR, tag, throwable = throwable)
    }

    fun e(tag: String, message: String, throwable: Throwable) {
        log(LogLevel.ERROR, tag, message, throwable)
    }

    fun wtf(tag: String, message: String) {
        log(LogLevel.ASSERT, tag, message)
    }
}