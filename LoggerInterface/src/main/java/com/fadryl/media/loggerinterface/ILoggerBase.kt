package com.fadryl.media.loggerinterface

/**
 * Created by Hoi Lung Lam (FaDr_YL) on 2022/10/26
 */
interface ILoggerBase {
    fun log(level: Int, tag: String, message: String? = null, throwable: Throwable? = null)
}