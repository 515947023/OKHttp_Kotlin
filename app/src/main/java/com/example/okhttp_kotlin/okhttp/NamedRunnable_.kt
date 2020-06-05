package com.example.okhttputils.okhttp

abstract class NamedRunnable_ : Runnable {

    override fun run() {
        execute()
    }

    abstract fun execute()
}