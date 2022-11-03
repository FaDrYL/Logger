package com.fadryl.media.loggerinterface

/**
 * Created by Hoi Lung Lam (FaDr_YL) on 2022/10/25
 */
interface ILoggable {
    fun isLoggable(level: Int): Boolean
}