package com.fadryl.media.logger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fadryl.media.logger.impl.PrettyLogcatLogAdapter
import com.fadryl.media.logger.impl.strategical.SimpleStrategyLogAdapter
import com.fadryl.media.loggerinterface.ILoggable
import com.fadryl.media.loggerinterface.LogLevel
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.addAdapter(PrettyLogcatLogAdapter())
        val tag = "Log-TEST"
        Logger.v(tag, "verbose test")
        Logger.log(LogLevel.VERBOSE, tag, "verbose test", Throwable("verbose throwable"))
        Logger.d(tag, "debug test")
        Logger.log(LogLevel.DEBUG, tag, "debug test", Throwable("debug throwable"))
        Logger.i(tag, "info test")
        Logger.log(LogLevel.INFO, tag, "info test", Throwable("info throwable"))
        Logger.w(tag, "warn test")
        Logger.log(LogLevel.WARN, tag, "warn test", Throwable("warn throwable"))
        Logger.e(tag, "error test")
        Logger.e(tag, "error test", IOException("a handmade error 1"))
        Logger.e(tag, IOException("a handmade error 2"))
        Logger.wtf(tag, "wtf test")
        Logger.log(100, "100 test")
        Logger.log(LogLevel.INFO, tag, "info test", Throwable())

        Logger.addAdapter(
            priority = 0,
            adapter = SimpleStrategyLogAdapter(
                formatter = { _, _, message, throwable ->
                    when {
                        message != null && throwable == null -> message
                        message == null && throwable != null -> "${throwable.message}, ${throwable.stackTrace}"
                        message != null && throwable != null -> "$message, ${throwable.message}, ${throwable.stackTrace}"
                        else -> ""
                    }
                },
                writer = {
                    it?.let {
                        Log.i("$tag-SSLA", it)
                    }
                },
                object : ILoggable {
                    override fun isLoggable(level: Int): Boolean {
                        return level != LogLevel.INFO
                    }
                }
            )
        )

        Logger.i(tag, "info test")
        Logger.w(tag, "warn test")
        Logger.wtf(tag, "wtf test")
    }
}