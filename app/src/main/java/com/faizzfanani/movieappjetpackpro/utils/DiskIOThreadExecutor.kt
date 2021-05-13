package com.faizzfanani.movieappjetpackpro.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskIOThreadExecutor internal constructor() : Executor {
    private val diskIO: Executor
    override fun execute(runnable: Runnable) {
        diskIO.execute(runnable)
    }

    init {
        diskIO = Executors.newSingleThreadExecutor()
    }
}