package com.example.okhttputils.okhttp.chain

import com.example.okhttputils.okhttp.RealCall_
import com.example.okhttputils.okhttp.Request_
import com.example.okhttputils.okhttp.Response_
import java.io.IOException
import java.lang.AssertionError

class RealInterceptorChain_(
    private val interceptors: List<Interceptor_>,
    private val index: Int,
    internal val request_: Request_,
    internal val call_: RealCall_

    ) :Interceptor_.Chain_ {
    override fun getRequest(): Request_ = request_

    override fun getResponse(request_: Request_): Response_ {
        if (index >= interceptors.size){
            throw AssertionError()
        }

        checkNotNull(interceptors) {IOException("interceptors is empty")}

        val interceptor_ = interceptors[index]

        val realInterceptorChain = RealInterceptorChain_(interceptors,index+1,request_,call_)

        val response_ = interceptor_.intercept(realInterceptorChain)

        return response_


    }
}