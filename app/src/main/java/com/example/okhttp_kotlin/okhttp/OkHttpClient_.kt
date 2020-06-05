package com.example.okhttputils.okhttp

class OkHttpClient_ internal constructor(builder: Builder_){

    val dispatcher: Dispatcher_ = builder.dispatcher

    fun newCall(request_: Request_): Call_ = RealCall_(this,request_)

    class Builder_ constructor(
        internal var dispatcher: Dispatcher_ = Dispatcher_()
    ){

        fun dispatcher(dispatcher: Dispatcher_) :Builder_ = apply {this.dispatcher = dispatcher}

        fun build(): OkHttpClient_{

            return OkHttpClient_(this)
        }

    }

}