package com.jmendezv.coroutineswithdb.seccion_05.leccion_36

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.*
import kotlin.system.*

suspend fun massiveRun4(action: suspend () -> Unit) {
   val n = 100  // number of coroutines to launch
   val k = 1000 // times an action is repeated by each coroutine
   val time = measureTimeMillis {
      coroutineScope { // scope for coroutines
         repeat(n) {
            launch {
               repeat(k) { action() }
            }
         }
      }
   }
   println("Completadas ${(n * k).format()} acciones en ${time.format()} ms")
}

val mutex = Mutex()
var counter4 = 0

fun main() = runBlocking {
   // multiples threads
   withContext(Dispatchers.Default) {
      massiveRun4 {
         // exclusión mútua
         mutex.withLock {
            counter4++
         }
      }
   }
   println("Contador = ${counter4.format()}")
}