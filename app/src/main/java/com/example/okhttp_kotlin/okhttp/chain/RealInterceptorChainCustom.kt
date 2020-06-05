package com.example.okhttputils.okhttp.chain

import com.example.okhttputils.okhttp.RealCallCustom
import com.example.okhttputils.okhttp.RequestCustom
import com.example.okhttputils.okhttp.ResponseCustom
import java.io.IOException
import java.lang.AssertionError

class RealInterceptorChainCustom(
    private val interceptorCustoms: List<InterceptorCustom>,
    private val index: Int,
    internal val requestCustom: RequestCustom,
    internal val call: RealCallCustom

    ) :InterceptorCustom.Chain {
    override fun getRequest(): RequestCustom = requestCustom

    override fun getResponse(requestCustom: RequestCustom): ResponseCustom {
        if (index >= interceptorCustoms.size){
            throw AssertionError()
        }

        checkNotNull(interceptorCustoms) {IOException("interceptors is empty")}

        val interceptor = interceptorCustoms[index]

        val realInterceptorChain = RealInterceptorChainCustom(interceptorCustoms,index+1,requestCustom,call)

        val response = interceptor.intercept(realInterceptorChain)

        return response


    }
}