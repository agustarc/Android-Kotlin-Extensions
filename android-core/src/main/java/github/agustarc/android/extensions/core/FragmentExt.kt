package github.agustarc.android.extensions.core

import android.app.Activity
import androidx.fragment.app.Fragment

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

inline fun Fragment.supplyContext(block: Activity.() -> Unit) {
    activity?.run { block(this) }
}

fun Fragment.finish() {
    supplyContext { finish() }
}
