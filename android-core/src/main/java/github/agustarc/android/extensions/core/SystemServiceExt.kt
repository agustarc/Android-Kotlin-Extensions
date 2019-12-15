package github.agustarc.android.extensions.core

import android.app.DownloadManager
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

val Context.windowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

val Context.connectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

val Context.inputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Context.clipboardManager
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

val Context.layoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

val Context.telephonyManager
    get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

val Context.downloadManager
    get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager