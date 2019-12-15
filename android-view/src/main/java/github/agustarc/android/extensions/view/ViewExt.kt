package github.agustarc.android.extensions.view

import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import github.agustarc.android.extensions.core.inputMethodManager

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

fun View.getString(@StringRes resId: Int): String = resources.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any): String =
    resources.getString(resId, *formatArgs)

fun View.getDimenSize(@DimenRes resId: Int): Int =
    resources.getDimensionPixelSize(resId)

fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE

inline fun <T : View> T.afterMeasured(crossinline block: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                block()
            }
        }
    })
}

fun View.openKeyboard() {
    context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.closeKeyboard(): Boolean {
    return try {
        context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (_: RuntimeException) {
        false
    }
}