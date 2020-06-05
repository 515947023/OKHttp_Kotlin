package com.example.okhttputils.okhttp

class Request_ internal constructor(
    val url:String,
    val mHeaderList: MutableMap<String, String>,
    val requestMethod:String,
    val requestBody:RequestBody_?
){
    open class Builder_ {
        private var url: String? = null
        private var mHeaderList = mutableMapOf<String,String>()
        private var requestMethod = "GET"
        private var requestBody_:RequestBody_? = null

        open fun addRequestHeader(key:String,value:String) = apply {
            this.mHeaderList[key] = value
        }

        open fun post(requestBody_:RequestBody_):Builder_=apply {
            this.requestMethod = "POST"
            this.requestBody_ = requestBody_
        }

        open fun build(): Request_ {
            return Request_(
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