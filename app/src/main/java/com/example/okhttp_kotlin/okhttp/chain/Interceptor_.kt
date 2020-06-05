package com.example.okhttputils.okhttp.chain

import com.example.okhttputils.okhttp.Request_
import com.example.okhttputils.okhttp.Response_
import java.io.IOException

interface Interceptor_ {
    @Throws(IOException::class)
    fun intercept(realInterceptorChain: RealInterceptorChain_): Response_


    open interface Chain_ {
        fun getRequest():Request_

        @Throws(IOException::class)
        fun getResponse(request_: Request_):Response_
    }
}