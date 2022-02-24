package ae.purehealth.dynamiccodeloadingimp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {
    lateinit var tvLog:TextView
    lateinit var btnOne:Button
    lateinit var btnTwo:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadDynamicClasses()
        tvLog = findViewById(R.id.tvLog)
        btnOne = findViewById(R.id.btnone)
        btnTwo = findViewById(R.id.btntwo)
        btnOne.setOnClickListener {
            add()
        }
        btnTwo.setOnClickListener {
            minus()
        }
    }

    fun loadDynamicClasses() {
        object : Thread() {
            override fun run() {
                LoadDynamicClasses(this@MainActivity).loadClassesFromApk()
            }
        }.start()
    }
    fun add() {
        if (LoadDynamicClasses.dymnamicClass!= null) {
            try {
                val addMethod: Method = LoadDynamicClasses.dymnamicClass!!.getMethod(
                    "first",
                    String,
                    String
                )
                val result = addMethod.invoke(null, 4, 2) as Int
                tvLog.setText(result.toString())
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
        }
    }
    fun minus() {
        if (LoadDynamicClasses.dymnamicClass!= null) {
            try {
                val addMethod: Method = LoadDynamicClasses.dymnamicClass!!.getMethod(
                    "second",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                val result = addMethod.invoke(null, 4, 2) as Int
                tvLog.setText(result.toString())
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
        }
    }

}

class test(){

}