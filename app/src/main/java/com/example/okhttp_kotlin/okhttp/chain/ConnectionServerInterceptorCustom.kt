package com.example.okhttputils.okhttp.chain

import android.util.Log
import com.example.okhttputils.okhttp.ResponseCustom
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import javax.net.ssl.SSLSocketFactory

/**
 * 链接服务器的拦截器
 */
class ConnectionServerInterceptorCustom : InterceptorCustom {

    override fun intercept(realInterceptorChainCustom: RealInterceptorChainCustom): ResponseCustom {

        val request = realInterceptorChainCustom.requestCustom

        val socket = if (getProtocol(request).equals("HTTP",true)) Socket(getHost(request), getPort(request))
            else SSLSocketFactory.getDefault().createSocket(getHost(request), getPort(request))

        //todo 请求
        val bufferWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))

        val requestAll = getRequestHeaderAll(request)

        Log.d(TAG, "intercept: requestAll = \n$requestAll")

        bufferWriter.write(requestAll)

        bufferWriter.flush()

        //todo 响应
        val bufferReader = BufferedReader(InputStreamReader(socket.getInputStream()))

        val response = ResponseCustom()

        val readLine = bufferReader.readLine()//读取首行

        val str = readLine.split(" ")
        response.statusCode = str[1].toInt()

        var readerLine =  bufferReader.readLine()

        while (readerLine != null){
            if ("".equals(readerLine)){     // 读到空行了，就代表下面就是 响应体了
                response.body = bufferReader.readLine()
                break
            }
            readerLine =  bufferReader.readLine()
        }

        return response


    }


}