package com.example.okhttputils.okhttp

import java.io.IOException

interface CallbackCustom {
    fun onFailure(callCustom: CallCustom, e: IOException)


    @Throws(IOException::class)
    fun onResponse(call: RealCallCustom.AsyncCall_, responseCustom: ResponseCustom)
}