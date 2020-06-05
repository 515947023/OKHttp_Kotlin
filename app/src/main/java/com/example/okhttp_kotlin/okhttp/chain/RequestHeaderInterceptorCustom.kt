package com.example.okhttputils.okhttp.chain

import com.example.okhttputils.okhttp.ResponseCustom


/**
 * 请求头拦截
 */
class RequestHeaderInterceptorCustom : InterceptorCustom {

    override fun intercept(realInterceptorChainCustom: RealInterceptorChainCustom): ResponseCustom {
        val request = realInterceptorChainCustom.requestCustom

        var mHeaderList = request.mHeaderList

        mHeaderList["Host"] = getHost(request)
        if ("POST".equals(request.requestMethod,true)){
            mHeaderList["Content-Length"] = request.requestBody!!.getBody().length.toString()
            mHeaderList["Content-Type"] = TYPE
        }

        return realInterceptorChainCustom.getResponse(request)
    }

}