package com.fadryl.media.loggerinterface

/**
 * Created by Hoi Lung Lam (FaDr_YL) on 2022/10/26
 */
object LogLevel {
    const val VERBOSE = 0b010
    const val DEBUG = 0b011
    const val INFO = 0b100
    const val WARN = 0b101
    const val ERROR = 0b110
    const val ASSERT = 0b111

    fun toLegacyLogLevel(level: Int) = (level and 0b111).coerceIn(2, 7)
}