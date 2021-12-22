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
    simpleSuspend()
}

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
