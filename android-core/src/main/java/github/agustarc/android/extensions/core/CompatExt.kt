package github.agustarc.android.extensions.core

import android.os.Build

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

inline fun belowApi(api: Int, included: Boolean = false, block: () -> Unit) {
    if (Build.VERSION.SDK_INT < if (included) api + 1 else api) {
        block()
    }
}

inline fun aboveApi(api: Int, included: Boolean = false, block: () -> Unit) {
    if (Build.VERSION.SDK_INT > if (included) api - 1 else api) {
        block()
    }
}