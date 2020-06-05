package com.example.okhttputils.okhttp

import java.net.URLEncoder

class RequestBody_ {


    var bodys =  mutableMapOf<String,String>()

    open fun addBody(key:String,value:String){
        bodys[URLEncoder.encode(key,"utf-8")] = URLEncoder.encode(value,"utf-8")
    }

    /**
     * 得到请求体信息
     */
    open fun getBody():String {
        var stringBuffer = StringBuffer()

        bodys.forEach{
            stringBuffer
                .append(it.key)
                .append("=")
                .append(it.value)
                .append("&")
        }
        if (stringBuffer.length>0){
            stringBuffer.deleteCharAt(stringBuffer.length-1)
        }

        return stringBuffer.toString()
    }
}