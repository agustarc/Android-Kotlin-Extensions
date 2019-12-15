package github.agustarc.android.extensions.core

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

val Int.DP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

val Float.DP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.SP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        toFloat(),
        Resources.getSystem().displayMetrics
    )

val Float.SP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )