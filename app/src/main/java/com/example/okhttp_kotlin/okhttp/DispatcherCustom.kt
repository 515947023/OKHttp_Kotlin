package com.example.okhttputils.okhttp

import com.example.okhttputils.okhttp.chain.threadFactory
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class DispatcherCustom {
    private val readyAsyncCalls = ArrayDeque<RealCallCustom.AsyncCall_>()

    /** Running asynchronous calls. Includes canceled calls that haven't finished yet. */
    private val runningAsyncCalls = ArrayDeque<RealCallCustom.AsyncCall_>()
    private val maxRequests = 64
    private val maxRequestsPerHost = 5

    internal fun enqueue(call: RealCallCustom.AsyncCall_) {

        if (runningAsyncCalls.size<maxRequests && runningCallsForHost(call)<maxRequestsPerHost){
            runningAsyncCalls.add(call)
            //在这里加两个试试
//            readyAsyncCalls.add(call)
//            readyAsyncCalls.add(call)
            executorService.execute(call)
        }else{
            readyAsyncCalls.add(call)
        }

    }
    private var executorServiceOrNull: ExecutorService? = null

    val executorService: ExecutorService
        get() {
            if (executorServiceOrNull == null) {
                executorServiceOrNull = ThreadPoolExecutor(0, Int.MAX_VALUE, 60, TimeUnit.SECONDS,
                    SynchronousQueue(), threadFactory("自定义okhttp", false)
                )
            }
            return executorServiceOrNull!!
        }


    private fun runningCallsForHost(call: RealCallCustom.AsyncCall_): Int {
//        for (runningAsyncCall in runningAsyncCalls) {
//
//        }
        return 1;
    }

    fun finished(asyncCall_: RealCallCustom.AsyncCall_) {
        if (readyAsyncCalls.size>0){
            var call_ =readyAsyncCalls.first
            readyAsyncCalls.removeFirst()
            runningAsyncCalls.add(call_)
            executorService.execute(call_)
        }
    }


}