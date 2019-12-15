# Android-Kotlin-Extensions
Useful Kotlin extension functions for Android

## Core
```kotlin
inline fun <reified T : Activity> Activity.startActivity() {
    val intent = Intent()
    intent.setClass(this, T::class.java)
    startActivity(intent)
}
```
```kotlin
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
```
```kotlin
val Context.windowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

val Context.connectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
```
```kotlin
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
```
[Show more extensions](https://github.com/AgustaRC/Android-Kotlin-Extensions/tree/master/android-core/src/main/java/github/agustarc/android/extensions/core)


## LiveData
```kotlin
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, body)
}
```
```kotlin
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
```
```kotlin
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
```
[Show more extensions](https://github.com/AgustaRC/Android-Kotlin-Extensions/tree/master/android-livedata/src/main/java/github/agustarc/android/extensions/livedata)

## View
```kotlin
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
```
```kotlin
fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE
```
```kotlin
fun ViewGroup.inflate(@LayoutRes resId: Int): View =
    LayoutInflater.from(context).inflate(resId, this, false)

val ViewGroup.children: List<View>
    get() = (0 until childCount).map { getChildAt(it) }

operator fun ViewGroup.get(index: Int): View? = getChildAt(index)

operator fun ViewGroup.plusAssign(child: View) = addView(child)

operator fun ViewGroup.minusAssign(child: View) = removeView(child)

operator fun ViewGroup.contains(child: View) = indexOfChild(child) != -1
```
[Show more extensions](https://github.com/AgustaRC/Android-Kotlin-Extensions/tree/master/android-view/src/main/java/github/agustarc/android/extensions/view)
