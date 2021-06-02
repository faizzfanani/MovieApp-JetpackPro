package com.faizzfanani.movieappjetpackpro.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

open class AppExecutor @VisibleForTesting constructor(
        diskIO: Executor,
        networkIO: Executor,
        mainThread: Executor
) {
    private val diskIO: Executor
    private val networkIO: Executor
    private val mainThread: Executor

    constructor() : this(
            DiskIOThreadExecutor(),
            Executors.newFixedThreadPool(THREAD_COUNT),
            MainThreadExecutor()
    ) {
    }

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHanlder: Handler = Handler(Looper.getMainLooper())
        override fun execute(runnable: Runnable) {
            mainThreadHanlder.post(runnable)
        }
    }

    companion object {
        private const val THREAD_COUNT = 3
    }

    init {
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }
}