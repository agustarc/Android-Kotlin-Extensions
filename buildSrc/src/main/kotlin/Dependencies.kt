/**
 * @author Leopold
 * https://medium.com/@joongwon
 * https://github.com/agustarc
 */
object AndroidX {
    private object Versions {
        const val appcompat = "1.0.2"
        const val material = "1.0.0"
        const val recyclerview = "1.1.0"
        const val constraintlayout = "1.1.3"
    }

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object JetPack {

    object Lifecycle {
        private const val VERSION = "2.1.0"
        private const val GROUP = "androidx.lifecycle"

        const val runtime = "$GROUP:lifecycle-runtime:$VERSION"
        const val extensions = "$GROUP:lifecycle-extensions:$VERSION"
        const val java8 = "$GROUP:lifecycle-common-java8:$VERSION"
        const val compiler = "$GROUP:lifecycle-compiler:$VERSION"
    }

}