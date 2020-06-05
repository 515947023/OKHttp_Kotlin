package com.example.okhttputils.okhttp

import java.io.IOException

interface Callback_ {
    fun onFailure(call: Call_, e: IOException)


    @Throws(IOException::class)
    fun onResponse(call: RealCall_.AsyncCall_, response: Response_)
}