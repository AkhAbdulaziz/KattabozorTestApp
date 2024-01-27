package com.axmedov.testapp.utils

import timber.log.Timber

fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}