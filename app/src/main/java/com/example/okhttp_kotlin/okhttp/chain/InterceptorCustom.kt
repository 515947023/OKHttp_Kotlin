package com.example.okhttputils.okhttp.chain

import com.example.okhttputils.okhttp.RequestCustom
import com.example.okhttputils.okhttp.ResponseCustom
import java.io.IOException

interface InterceptorCustom {
    @Throws(IOException::class)
    fun intercept(realInterceptorChainCustom: RealInterceptorChainCustom): ResponseCustom


    open interface Chain {
        fun getRequest():RequestCustom

        @Throws(IOException::class)
        fun getResponse(requestCustom: RequestCustom):ResponseCustom
    }
}