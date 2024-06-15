package com.jmendezv.coroutineswithdb.seccion_05.leccion_36

/*
* LECCIÓN 36: ¿CÓMO COMPARTIR ESTADO MUTABLE ENTRE CORRUTINAS?
*
* */
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.*

suspend fun massiveRun(action: suspend () -> Unit) {
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
   println("Completadas ${(n * k).format()} acciones en $time ms")
}
// @Volatile
// var counter = 0
val counter = AtomicInteger()

fun main() = runBlocking {
   // multiples threads
   withContext(Dispatchers.Default) {
      massiveRun {
         // counter++
         // println(Thread.currentThread()) // Varios hilos
         counter.incrementAndGet()
      }
   }
   println("Contador = ${counter.format()}")
}