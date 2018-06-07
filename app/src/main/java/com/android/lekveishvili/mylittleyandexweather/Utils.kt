package com.android.lekveishvili.mylittleyandexweather

inline fun Double.tempToString(): String {
    return if (this > 0) {
        "+" + this.toInt().toString() + " C°"
    } else {
        this.toInt().toString() + " C°"
    }
}
