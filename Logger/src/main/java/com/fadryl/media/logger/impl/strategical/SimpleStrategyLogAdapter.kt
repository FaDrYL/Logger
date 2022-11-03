package com.fadryl.media.logger.impl.strategical

import com.fadryl.media.loggerinterface.ILogAdapter
import com.fadryl.media.loggerinterface.ILoggable

/**
 * Created by Hoi Lung Lam (FaDr_YL) on 2022/10/27
 */
class SimpleStrategyLogAdapter(
    private val formatter: (level: Int, tag: String, message: String?, throwable: Throwable?) -> String?,
    private val writer: (message: String?) -> Unit,
    private val loggable: ILoggable
): ILogAdapter {
    override fun isLoggable(level: Int): Boolean {
        return loggable.isLoggable(level)
    }

    override fun log(level: Int, tag: String, message: String?, throwable: Throwable?) {
       format(level, tag, message, throwable)
    }

    private fun format(level: Int, tag: String, message: String?, throwable: Throwable?) {
        val formatted = formatter(level, tag, message, throwable)
        logInternal(formatted)
    }

    private fun logInternal(message: String?) {
        writer(message)
    }
}