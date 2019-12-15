package github.agustarc.android.extensions.view

import android.widget.EditText

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

val EditText.value
    get() = text?.toString() ?: ""

fun EditText.isEmpty() = value.isEmpty()