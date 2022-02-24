package ae.purehealth.dynamiccodeloadingimp

import dalvik.system.DexClassLoader
import ae.purehealth.dynamiccodeloadingimp.LoadDynamicClasses
import android.content.Context
import java.io.File
import java.lang.Exception

class LoadDynamicClasses(private val context: Context) {
    fun loadClassesFromApk() {
//        val apkPath = context.filesDir.absolutePath + "/app-debug.apk"
        val apkPath =  getFileFromAssets(context, "app-debug.apk").absolutePath

        val classLoader =
            DexClassLoader(apkPath, context.cacheDir.absolutePath, null, this.javaClass.classLoader)
        try {
            dymnamicClass = classLoader.loadClass("ae.purehealth.dynamiccodeloadingtesting.DynamicCass")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun getFileFromAssets(context: Context, fileName: String): File = File(context.cacheDir, fileName)
        .also {
            if (!it.exists()) {
                it.outputStream().use { cache ->
                    context.assets.open(fileName).use { inputStream ->
                        inputStream.copyTo(cache)
                    }
                }
            }
        }
    companion object {
        private const val TAG = "LoadDynamicClasses"
        var dymnamicClass: Class<*>? = null
    }
}