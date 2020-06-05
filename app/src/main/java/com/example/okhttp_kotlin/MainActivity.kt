package com.example.okhttp_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.okhttputils.okhttp.*
import java.io.IOException
import com.example.okhttputils.okhttp.chain.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun get(view: View) {

        val path = "http://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2"
        val okHttpClient_ = OkHttpClient_.Builder_().build()

        val request_ = Request_.Builder_().url(path).build()

        val call_ = okHttpClient_.newCall(request_)

        call_.enqueue(object : Callback_ {
            override fun onFailure(call: Call_, e: IOException) {
                Log.d(TAG, "onFailure: e = \n${e.toString()}")

            }

            override fun onResponse(call: RealCall_.AsyncCall_, response: Response_) {
                Log.d(TAG, "onResponse: response = \n${response.body}")
            }

        })

    }
    fun post(view: View) {
        val path = "http://restapi.amap.com/v3/weather/weatherInfo"
        val requestBody2 = RequestBody_()
        requestBody2.addBody("city", "110101")
        requestBody2.addBody("key", "13cb58f5884f9749287abbead9c658f2")
        val okHttpClient_ = OkHttpClient_.Builder_().build()

        val request_ = Request_.Builder_().post(requestBody2).url(path).build()

        val call_ = okHttpClient_.newCall(request_)

        call_.enqueue(object :Callback_{
            override fun onFailure(call: Call_, e: IOException) {
                Log.d(TAG, "\n onFailure: e = \n${e.toString()}")

            }

            override fun onResponse(call: RealCall_.AsyncCall_, response: Response_) {
                Log.d(TAG, "onResponse: response = \n${response.body}")
            }

        })

    }
}