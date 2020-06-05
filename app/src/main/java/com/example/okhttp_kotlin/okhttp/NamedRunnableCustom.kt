package com.example.okhttputils.okhttp

abstract class NamedRunnableCustom : Runnable {

    override fun run() {
        execute()
    }

    abstract fun execute()
}