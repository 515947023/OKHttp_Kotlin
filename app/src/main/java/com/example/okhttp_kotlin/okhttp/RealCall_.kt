package com.example.okhttputils.okhttp

import com.example.okhttputils.okhttp.chain.ConnectionServerInterceptor
import com.example.okhttputils.okhttp.chain.Interceptor_
import com.example.okhttputils.okhttp.chain.RequestHeaderInterceptor
import com.example.okhttputils.okhttp.chain.RealInterceptorChain_
import java.io.IOException

class RealCall_(
    val client: OkHttpClient_,
    val request_: Request_
    ) : Call_ {
    private var executed = false

    override fun enqueue(responseCallback: Callback_) {
        synchronized(this){
            check(!executed){"Already Executed"}
            executed = true
        }

        client.dispatcher.enqueue(AsyncCall_(responseCallback))

    }

    inner class AsyncCall_(private val responseCallback: Callback_)
        : NamedRunnable_() {

        override fun execute() {
            var signalledCallback:Boolean = false

            try {
                var response_ = getResponseWithInterceptorChain()

                responseCallback.onResponse(this, response_)
            }catch (e:IOException){

            }finally {
                client.dispatcher.finished(this)

            }


        }

        @Throws(IOException::class)
        fun getResponseWithInterceptorChain():Response_{
            val interceptors = mutableListOf<Interceptor_>()
            interceptors += RequestHeaderInterceptor()
            interceptors += ConnectionServerInterceptor()

            val chain_ = RealInterceptorChain_(interceptors,0,request_,this@RealCall_)

            return chain_.getResponse(request_)
        }
    }
}