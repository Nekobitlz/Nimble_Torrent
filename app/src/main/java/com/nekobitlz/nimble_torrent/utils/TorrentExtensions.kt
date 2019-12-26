package com.nekobitlz.nimble_torrent.utils

fun Long.toHumanReadable(): String {
    var current = this.toFloat()
    var count = 0

    while (current > 1024 && count < 5) {
        current /= 1024
        count++
    }

    val result = String.format("%.2f", current)

    return when (count) {
        0 -> "$result B"
        1 -> "$result Kb"
        2 -> "$result Mb"
        3 -> "$result Gb"
        4 -> "$result Tb"
        else -> "$result Pb"
    }
}

fun main() {
    var long: Long = 24330234
    println(long.toHumanReadable())
    long = 69581268
    println(long.toHumanReadable())
    long = 24
    println(long.toHumanReadable())
    long = 1025
    println(long.toHumanReadable())
    long = 102410241024
    println(long.toHumanReadable())
    long = 1024102410241024102
    println(long.toHumanReadable())
}