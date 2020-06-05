package com.example.okhttputils.okhttp

class RequestCustom internal constructor(
    val url:String,
    val mHeaderList: MutableMap<String, String>,
    val requestMethod:String,
    val requestBody:RequestBodyCustom?
){
    open class Builder_ {
        private var url: String? = null
        private var mHeaderList = mutableMapOf<String,String>()
        private var requestMethod = "GET"
        private var requestBody_:RequestBodyCustom? = null

        open fun addRequestHeader(key:String,value:String) = apply {
            this.mHeaderList[key] = value
        }

        open fun post(requestBody_:RequestBodyCustom):Builder_=apply {
            this.requestMethod = "POST"
            this.requestBody_ = requestBody_
        }

        open fun build(): RequestCustom {
            return RequestCustom(
                checkNotNull(url) { "url == null" },
                mHeaderList,
                requestMethod,
                requestBody_
            )
        }

       open fun url(url:String): Builder_ =apply{
            this.url = url
        }

    }
}