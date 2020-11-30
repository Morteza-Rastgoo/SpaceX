package com.dynamo.spacex.util.extensions

import android.view.View

/**
 * Makes View visible
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Makes View invisible
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Makes View invisible
 */
fun View.gone() {
    visibility = View.GONE
}