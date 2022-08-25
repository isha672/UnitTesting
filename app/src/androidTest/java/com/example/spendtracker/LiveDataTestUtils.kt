package com.example.spendtracker

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@VisibleForTesting(otherwise = VisibleForTesting.NONE)


fun <T> LiveData<T>.getOrAwaitValue(): T {  //extension function to test live data
    //we need to define a generic function that will work for all live datas so will define type as T

    var data: T? = null
    //bcoz here we need to wait in case of live data until it gets value
    val latch =
        CountDownLatch(1)  // we use waiting for other thread to complete so whenever our observer called and we will get live data value

    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            this@getOrAwaitValue.removeObserver(this)
            latch.countDown() //to stop waiting for live data value
        }

    }

    this.observeForever(observer) // to attach observer to our live data
    try {
        if (!latch.await(
                2,
                TimeUnit.SECONDS
            )
        ) {   //this latch will wait until the live data get its value
            throw TimeoutException("LiveData never get its value")
        }
    } finally {
        this.removeObserver(observer)
    }
    return data as T

}
