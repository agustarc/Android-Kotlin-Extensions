package github.agustarc.android.extensions.core

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

fun Context.getInteger(@IntegerRes resId: Int) =
    resources.getInteger(resId)

fun Context.getDimenSize(@DimenRes resId: Int) =
    resources.getDimensionPixelSize(resId)

fun Context.getCompatColor(@ColorRes resId: Int) =
    ContextCompat.getColor(this, resId)

fun Context.isActivityFinishing(): Boolean {
    return this is Activity && isFinishing
}

fun Context.isActivityDestroyed(): Boolean {
    return this is Activity && isDestroyed
}

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Context.toast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text.orEmpty(), duration).show()
}