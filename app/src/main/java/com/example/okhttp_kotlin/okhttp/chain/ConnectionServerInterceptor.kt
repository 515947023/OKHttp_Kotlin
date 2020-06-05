package com.example.okhttputils.okhttp.chain

import android.util.Log
import com.example.okhttputils.okhttp.Response_
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

/**
 * 链接服务器的拦截器
 */
class ConnectionServerInterceptor : Interceptor_ {

    override fun intercept(realInterceptorChain: RealInterceptorChain_): Response_ {

        val request_ = realInterceptorChain.request_

        val socket = Socket(getHost(request_), getPort(request_))

        //todo 请求
        val bufferWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))

        val requestAll = getRequestHeaderAll(request_)

        Log.d(TAG, "intercept: requestAll = \n$requestAll")

        bufferWriter.write(requestAll)

        bufferWriter.flush()

        //todo 响应
        val bufferReader = BufferedReader(InputStreamReader(socket.getInputStream()))

        val response_ = Response_()

        val readLine = bufferReader.readLine()//读取首行

        val str = readLine.split(" ")
        response_.statusCode = str[1].toInt()

        var readerLine =  bufferReader.readLine()

        while (readerLine != null){
            if ("".equals(readerLine)){     // 读到空行了，就代表下面就是 响应体了
                response_.body = bufferReader.readLine()
                break
            }
            readerLine =  bufferReader.readLine()
        }

        return response_


    }


}