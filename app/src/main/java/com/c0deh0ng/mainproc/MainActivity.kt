package com.c0deh0ng.mainproc

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val file = File("MainProC/json_info/json_info.json")


//
//        val ss = getAssetJsonData(this, file.name)
//        val ss = (getJsonFile(this, file.name, JsonDataTest::class.java) as JsonDataTest).version
//

        runCatching {
//            val filePath = file1.absolutePath
            val ff = filesDir.path
            Log.d("TAG", "test here 1 ${ff}")
            val path: String = getFilesDir().getAbsolutePath()
            val file1 = File(path + "/json_info.json")
            val length = file1.length().toInt()

            val bytes = ByteArray(length)

            val `in` = FileInputStream(file1)
            try {
                `in`.read(bytes)
            } finally {
                `in`.close()
            }

            val contents = String(bytes)
            Log.d("TAG", "test here 2 = ${contents}")
        }.onFailure {
            Log.d("TAG", "test here error :: ${it}")
        }


    }

    fun getJsonFile(context: Context?, fileName: String?, dataClass: Class<*>): Any? {
        if (fileName.isNullOrEmpty() || context == null) return null
        return try {
            val jsonStr = context.assets.open(fileName).bufferedReader().use { it.readText() }
            Gson().fromJson(jsonStr, dataClass)
        } catch (e: IOException) {
            null
        }
    }

    fun getAssetJsonData(context: Context, fileName: String): String? {
        var json: String? = null
        json = try {
            val `is`: InputStream = context.assets.open(fileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}