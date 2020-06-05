package com.example.okhttputils.okhttp.chain

import com.example.okhttputils.okhttp.Response_


/**
 * 请求头拦截
 */
class RequestHeaderInterceptor : Interceptor_ {

    override fun intercept(realInterceptorChain: RealInterceptorChain_): Response_ {
        val request = realInterceptorChain.request_

        var mHeaderList = request.mHeaderList

        mHeaderList["Host"] = getHost(request)
        if ("POST".equals(request.requestMethod,true)){
            mHeaderList["Content-Length"] = request.requestBody!!.getBody().length.toString()
            mHeaderList["Content-Type"] = TYPE
        }

        return realInterceptorChain.getResponse(request)
    }

}