package com.example.okhttputils.okhttp.chain

import com.example.okhttputils.okhttp.Request_
import java.net.URL
import java.util.concurrent.ThreadFactory

// 表单提交Type application/x-www-form-urlencoded
val TYPE = "application/x-www-form-urlencoded"
val K = " "
val VIERSION = "HTTP/1.1"
val GRGN = "\r\n"
val TAG = "OKHttp_Kotlin"

/**
 * 线程工程
 */
fun threadFactory(
    name: String,
    daemon: Boolean
): ThreadFactory = ThreadFactory { runnable ->
    Thread(runnable, name).apply {
        isDaemon = daemon
    }
}

/**
 * todo 域名
 */
fun getHost(request_: Request_):String{
    var url = URL(request_.url)
    return url.host
}

/**
 * todo 端口
 */
fun getPort(request_: Request_):Int {
    var url = URL(request_.url)
    return if (url.port == -1) url.defaultPort else url.port
}

fun getRequestHeaderAll(request_: Request_): String {

    val url = URL(request_.url)

    val file = url.file

    val stringBuffer = StringBuffer()

    // TODO 拼接 请求头 的 请求行  GET /v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2 HTTP/1.1\r\n
    stringBuffer.append(request_.requestMethod)
        .append(K)
        .append(file)
        .append(K)
        .append(VIERSION)
        .append(GRGN)


    // TODO 获取请求集 进行拼接
    /**
     * Content-Length: 48\r\n
     * Host: restapi.amap.com\r\n
     * Content-Type: application/x-www-form-urlencoded\r\n
     */
    if (!request_.mHeaderList.isEmpty()){
        var map = request_.mHeaderList
        map.forEach(){
            stringBuffer
                .append(it.key)
                .append(":").append(K)
                .append(it.value)
                .append(GRGN);
        }
        // 拼接空行，代表下面的POST，请求体了
        stringBuffer.append(GRGN)
    }


    // TODO POST请求才有 请求体的拼接
    if ("POST".equals(request_.requestMethod, ignoreCase = true)) {
        stringBuffer.append(request_.requestBody!!.getBody()).append(GRGN)
    }
    return stringBuffer.toString()
}