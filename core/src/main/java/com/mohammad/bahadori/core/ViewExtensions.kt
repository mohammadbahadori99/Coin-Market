package com.mohammad.bahadori.core
import javax.inject.Qualifier
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.show() {
    visibility = VISIBLE
}

fun View.hide() {
    visibility = GONE
}

fun MenuItem.hide() {
    isVisible = false
}

fun MenuItem.show() {
    isVisible = true
}
