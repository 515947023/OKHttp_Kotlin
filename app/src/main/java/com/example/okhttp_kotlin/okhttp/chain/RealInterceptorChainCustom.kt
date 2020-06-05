package com.example.okhttputils.okhttp.chain

import com.example.okhttputils.okhttp.RealCallCustom
import com.example.okhttputils.okhttp.RequestCustom
import com.example.okhttputils.okhttp.ResponseCustom
import java.io.IOException
import java.lang.AssertionError

class RealInterceptorChainCustom(
    private val interceptorCustoms: List<InterceptorCustom>,
    private val index: Int,
    internal val request_Custom_: RequestCustom,
    internal val call_: RealCallCustom

    ) :InterceptorCustom.Chain_ {
    override fun getRequest(): RequestCustom = request_Custom_

    override fun getResponse(request_Custom_: RequestCustom): ResponseCustom {
        if (index >= interceptorCustoms.size){
            throw AssertionError()
        }

        checkNotNull(interceptorCustoms) {IOException("interceptors is empty")}

        val interceptor_ = interceptorCustoms[index]

        val realInterceptorChain = RealInterceptorChainCustom(interceptorCustoms,index+1,request_Custom_,call_)

        val response_ = interceptor_.intercept(realInterceptorChain)

        return response_


    }
}