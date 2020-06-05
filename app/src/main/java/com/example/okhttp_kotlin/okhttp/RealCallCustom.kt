package com.example.okhttputils.okhttp

import com.example.okhttputils.okhttp.chain.ConnectionServerInterceptorCustom
import com.example.okhttputils.okhttp.chain.InterceptorCustom
import com.example.okhttputils.okhttp.chain.RequestHeaderInterceptorCustom
import com.example.okhttputils.okhttp.chain.RealInterceptorChainCustom
import java.io.IOException

class RealCallCustom(
    val clientCustom: OkHttpClientCustom,
    val request_Custom_: RequestCustom
    ) : CallCustom {
    private var executed = false

    override fun enqueue(responseCallbackCustom: CallbackCustom) {
        synchronized(this){
            check(!executed){"Already Executed"}
            executed = true
        }

        clientCustom.dispatcher.enqueue(AsyncCall_(responseCallbackCustom))

    }

    inner class AsyncCall_(private val responseCallbackCustom: CallbackCustom)
        : NamedRunnableCustom() {

        override fun execute() {
            var signalledCallback:Boolean = false

            try {
                var response_ = getResponseWithInterceptorChain()

                responseCallbackCustom.onResponse(this, response_)
            }catch (e:IOException){

            }finally {
                clientCustom.dispatcher.finished(this)

            }


        }

        @Throws(IOException::class)
        fun getResponseWithInterceptorChain():ResponseCustom{
            val interceptors = mutableListOf<InterceptorCustom>()
            interceptors += RequestHeaderInterceptorCustom()
            interceptors += ConnectionServerInterceptorCustom()

            val chain_ = RealInterceptorChainCustom(interceptors,0,request_Custom_,this@RealCallCustom)

            return chain_.getResponse(request_Custom_)
        }
    }
}