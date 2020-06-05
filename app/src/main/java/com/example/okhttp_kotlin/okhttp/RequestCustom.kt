package com.example.okhttputils.okhttp

class RequestCustom internal constructor(
    val url:String,
    val mHeaderList: MutableMap<String, String>,
    val requestMethod:String,
    val requestBody:RequestBodyCustom?
){
    open class Builder {
        private var url: String? = null
        private var mHeaderList = mutableMapOf<String,String>()
        private var requestMethod = "GET"
        private var requestBody:RequestBodyCustom? = null

        open fun addRequestHeader(key:String,value:String) = apply {
            this.mHeaderList[key] = value
        }

        open fun post(requestBody:RequestBodyCustom):Builder=apply {
            this.requestMethod = "POST"
            this.requestBody = requestBody
        }

        open fun build(): RequestCustom {
            return RequestCustom(
                checkNotNull(url) { "url == null" },
                mHeaderList,
                requestMethod,
                requestBody
            )
        }

       open fun url(url:String): Builder =apply{
            this.url = url
        }

    }
}