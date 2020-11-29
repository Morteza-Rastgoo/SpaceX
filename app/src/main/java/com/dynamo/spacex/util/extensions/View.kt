package com.dynamo.spacex.util.extensions

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.children

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

/**
 * shows softKeyboard in the selected view
 */
fun View.showKeyboard() {
    if (this is EditText) {
        this.requestFocus()
    }
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(
        this,
        InputMethodManager.SHOW_IMPLICIT
    )
}

/**
 * hide softKeyboard
 */
fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * FadeIn animation with duration time(default is 1500 milli second) and that get block to run on end of animation
 */
fun View.fadeIn(duration: Long = 500, block: () -> Unit) {
    this.animate().alpha(1.0f).setDuration(duration)
        .setInterpolator(DecelerateInterpolator())
        .withEndAction {
            this.animate().alpha(0.0f).setDuration(duration)
                .setInterpolator(AccelerateInterpolator()).start()
            block.invoke()
        }.start()
}

/**
 * FadeOut animation with duration time(default is 1500 milli second) and that get block to run on end of animation
 */
fun View.fadeOut(duration: Long = 500, block: () -> Unit) {
    this.animate().alpha(0.0f).setDuration(duration)
        .setInterpolator(DecelerateInterpolator())
        .withEndAction {
            this.animate().alpha(1.0f).setDuration(duration)
                .setInterpolator(AccelerateInterpolator()).start()
            block.invoke()
        }.start()
}

/**
 * FadeOut animation with duration time(default is 1500 milli second) and that get block to run on end of animation
 */
fun View.slideOutToRight(
    translate: Float = width.toFloat(),
    duration: Long = 500,
    block: () -> Unit
) {
    this.animate().translationXBy(translate).setDuration(duration)
        .setInterpolator(LinearInterpolator())
        .withEndAction {
            block.invoke()
        }.start()
}

/**
 * FadeOut animation with duration time(default is 1500 milli second) and that get block to run on end of animation
 */
fun View.slideInFromLeft(
    translate: Float = width.toFloat(),
    duration: Long = 500, block: () -> Unit
) {
    this.translationX = -translate
    this.animate().translationXBy(translate).setDuration(duration)
        .setInterpolator(LinearInterpolator())
        .withEndAction {
            block.invoke()
        }.start()
}

/**
 * changing the color of some view background
 */
fun View.changeBackgroundColor(color: Int) = (this.background as GradientDrawable).setColor(color)

/**
 * listener to make use of setOnTouchListener with MotionEvent.ACTION_UP
 */
fun View.setOnTouchUp(block: () -> Unit) {
    setOnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                block.invoke()
                return@setOnTouchListener true
            }
        }
        return@setOnTouchListener false
    }
}

/**
 * enabling some view and all children's if exist
 */
fun View.setAllEnabled(enabled: Boolean) {
    isEnabled = enabled
    if (this is ViewGroup) children.forEach { child -> child.setAllEnabled(enabled) }
}


inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

/**
 * Easily Set margin to view by dp
 */
fun View.margin(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null,
    start: Float? = null,
    end: Float? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        val lp = this
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
        start?.run { MarginLayoutParamsCompat.setMarginStart(lp, dpToPx(this)) }
        end?.run { MarginLayoutParamsCompat.setMarginEnd(lp, dpToPx(this)) }
    }
}

fun View.setMargin(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val params = (layoutParams as? ViewGroup.MarginLayoutParams)
    params?.setMargins(
        left ?: params.leftMargin,
        top ?: params.topMargin,
        right ?: params.rightMargin,
        bottom ?: params.bottomMargin
    )
    layoutParams = params
}

fun View.setMarginRes(
    @DimenRes left: Int? = null,
    @DimenRes top: Int? = null,
    @DimenRes right: Int? = null,
    @DimenRes bottom: Int? = null
) {
    setMargin(
        if (left == null) null else resources.getDimensionPixelSize(left),
        if (top == null) null else resources.getDimensionPixelSize(top),
        if (right == null) null else resources.getDimensionPixelSize(right),
        if (bottom == null) null else resources.getDimensionPixelSize(bottom)
    )
}

var View.horizontalMargin: Int
    get() = throw UnsupportedOperationException("No getter for property")
    set(@Px horizontalMargin) = setMargin(left = horizontalMargin, right = horizontalMargin)

var View.horizontalMarginRes: Int
    get() = throw UnsupportedOperationException("No getter for property")
    set(@DimenRes horizontalMarginRes) {
        horizontalMargin = resources.getDimensionPixelSize(horizontalMarginRes)
    }

var View.verticalMargin: Int
    get() = throw UnsupportedOperationException("No getter for property")
    set(@Px verticalMargin) = setMargin(top = verticalMargin, bottom = verticalMargin)

var View.verticalMarginRes: Int
    get() = throw UnsupportedOperationException("No getter for property")
    set(@DimenRes horizontalMarginRes) {
        verticalMargin = resources.getDimensionPixelSize(horizontalMarginRes)
    }