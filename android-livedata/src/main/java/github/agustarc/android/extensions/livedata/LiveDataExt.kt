package github.agustarc.android.extensions.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations

/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */

fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, body)
}

inline fun <T> LiveData<T>.filter(crossinline predicate: (T?) -> Boolean): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    mediator.addSource(this) {
        if (predicate(it)) {
            mediator.value = it
        }
    }

    return mediator
}

fun <T> LiveData<T>.take(count: Int): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    var taken = 0
    mediator.addSource(this) {
        if (taken < count) {
            mediator.value = it
            taken++
        } else {
            mediator.removeSource(this)
        }
    }

    return mediator
}

inline fun <T> LiveData<T>.takeUntil(crossinline predicate: (T?) -> Boolean): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    mediator.addSource(this) {
        if (predicate(it)) {
            mediator.value = it
        } else {
            mediator.removeSource(this)
        }
    }

    return mediator
}

fun <T> LiveData<T>.skip(count: Int): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    var skipped = 0
    mediator.addSource(this) {
        if (skipped >= count) {
            mediator.value = it
        } else {
            skipped++
        }
    }

    return mediator
}

inline fun <T> LiveData<T>.skipUntil(crossinline predicate: (T?) -> Boolean): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    var metPredicate = false
    mediator.addSource(this) {
        if (metPredicate || predicate(it)) {
            metPredicate = true
            mediator.value = it
        }
    }

    return mediator
}

fun <A, B, Result> LiveData<A>.combineLatest(
    other: LiveData<B>,
    combiner: (A, B) -> Result
): LiveData<Result> {
    val mediator = MediatorLiveData<Result>()
    mediator.addSource(this) { a ->
        val b = other.value
        if (b != null) {
            mediator.value = combiner(a, b)
        }
    }
    mediator.addSource(other) { b ->
        val a = this@combineLatest.value
        if (a != null) {
            mediator.value = combiner(a, b)
        }
    }
    return mediator
}

fun <A, B, C, Result> LiveData<A>.combineLatest(
    other1: LiveData<B>,
    other2: LiveData<C>,
    combiner: (A, B, C) -> Result
): LiveData<Result> {
    val mediator = MediatorLiveData<Result>()
    mediator.addSource(this) { a ->
        val b = other1.value
        val c = other2.value
        if (b != null && c != null) {
            mediator.value = combiner(a, b, c)
        }
    }
    mediator.addSource(other1) { b ->
        val a = this@combineLatest.value
        val c = other2.value
        if (a != null && c != null) {
            mediator.value = combiner(a, b, c)
        }
    }
    mediator.addSource(other2) { c ->
        val a = this@combineLatest.value
        val b = other1.value
        if (a != null && b != null) {
            mediator.value = combiner(a, b, c)
        }
    }
    return mediator
}