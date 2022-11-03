package com.fadryl.media.loggermanager

import com.fadryl.media.loggerinterface.ILogAdapter
import com.fadryl.media.loggerinterface.ILoggerBase

class LoggerManager private constructor(): ILoggerBase {
    var isEnable: Boolean = true
    private val mPrioritisedAdapters = listOf<MutableSet<ILogAdapter>>(
        mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf()
    )

    companion object {
        val instance by lazy { LoggerManager() }
    }

    fun addAdapter(priority: Int = 2, adapter: ILogAdapter) {
        mPrioritisedAdapters[priority].add(adapter)
    }

    fun removeAdapter(priority: Int = 2, adapter: ILogAdapter) {
        mPrioritisedAdapters[priority].remove(adapter)
    }

    fun removeAdapterFromAll(adapter: ILogAdapter) {
        mPrioritisedAdapters.forEach { it.remove(adapter) }
    }
    
    private fun forAllLoggableAdapters(level: Int, callable: (ILogAdapter) -> Unit) {
        mPrioritisedAdapters.forEach { adapters ->
            adapters.forEach { adapter ->
                if (adapter.isLoggable(level)) {
                    callable.invoke(adapter)
                }
            }
        }
    }

    override fun log(level: Int, tag: String, message: String?, throwable: Throwable?) {
        forAllLoggableAdapters(level) { it.log(level, tag, message, throwable) }
    }
}