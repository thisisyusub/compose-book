package com.yusubov.composebook.core.extensions

import kotlin.math.roundToInt

internal fun Float.format(decimals: Int): String {
    val factor = when (decimals) {
        1 -> 10f; 2 -> 100f; 3 -> 1000f; else -> 1f
    }
    val rounded = (this * factor).roundToInt() / factor
    val str = rounded.toString()
    val dot = str.indexOf('.')
    return if (dot == -1) "$str.${"0".repeat(decimals)}"
    else {
        val have = str.length - dot - 1
        if (have >= decimals) str.substring(0, dot + decimals + 1)
        else str + "0".repeat(decimals - have)
    }
}