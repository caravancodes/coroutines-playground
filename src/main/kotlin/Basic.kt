import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
 * Created by faisalamir on 22/12/21
 * coroutines-plyaground
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */

fun main() {
    Basic().repeater()
}

class Basic {

    fun simpleRunBlocking() {
        runBlocking { // this: CoroutineScope
            launch { // launch a new coroutine and continue
                delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                println("World!") // print after delay
            }
            println("Hello") // main coroutine continues while a previous one is delayed
        }
    }

    fun simpleSuspend() {
        runBlocking {
            launch { doWorld() }
            println("Hello")
        }
    }

    // this is your first suspending function
    suspend fun doWorld() {
        delay(1000L)
        println("World!")
    }

    // Sequentially executes doWorld followed by "Done"
    fun multipleSuspend() {
        runBlocking {
//        doMultiple()
            loopDelay("Hit", "Def", 1000L)
            println("Done")
        }
    }

    // Concurrently executes both sections
    suspend fun doMultiple() = coroutineScope { // this: CoroutineScope
        launch {
            delay(2000L)
            println("World 2")
        }
        launch {
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }

    suspend fun loopDelay(state1: String, state2: String, time: Long) = coroutineScope {
        launch {
            for (i in 1..6) {
                delay(time + 1000L)
                println(state1)
            }
        }
        launch {
            for (i in 1..6) {
                delay(time)
                println(state2)
            }
        }
    }


    fun repeater() {
        runBlocking {
            launch {
                repeat(10) { i -> // launch a lot of coroutines
                    delay(1000L)
                    println("$i. WOW")
                }
            }

        }
    }

    fun joinJob() = runBlocking {
        val job = launch { // launch a new coroutine and keep a reference to its Job
            delay(1000L)
            println("World!")
        }
        println("Hello")
        job.join() // wait until child coroutine completes
        println("Done")
    }
}