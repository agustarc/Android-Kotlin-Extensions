package github.agustarc.android.extensions.view

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

fun <T, V : RecyclerView.ViewHolder> ListAdapter<T, V>.isEmpty(): Boolean {
    return itemCount <= 0
}

fun <T, V : RecyclerView.ViewHolder> ListAdapter<T, V>.isLastItemPosition(position: Int): Boolean =
    position == itemCount - 1