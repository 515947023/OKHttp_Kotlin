package com.example.okhttputils.okhttp

class OkHttpClientCustom internal constructor(builder: Builder_){

    val dispatcher: DispatcherCustom = builder.dispatcher

    fun newCall(request_Custom_: RequestCustom): CallCustom = RealCallCustom(this,request_Custom_)

    class Builder_ constructor(
        internal var dispatcher: DispatcherCustom = DispatcherCustom()
    ){

        fun dispatcher(dispatcher: DispatcherCustom) :Builder_ = apply {this.dispatcher = dispatcher}

        fun build(): OkHttpClientCustom{

            return OkHttpClientCustom(this)
        }

    }

}