package com.jmendezv.coroutineswithdb.seccion_05.leccion_36

import kotlinx.coroutines.*
import kotlin.system.*


/*
* Este código funciona muy lentamente porque realiza un confinamiento de hilos.
* Cada incremento individual cambia del contexto Dispatchers.Default de múltiples hilos
* al contexto de un solo hilo usando el bloque withContext(counterContext).
*
* */
suspend fun massiveRun2(action: suspend () -> Unit) {
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


val counterContext = newSingleThreadContext("CounterContext")
var counter2 = 0

fun main() = runBlocking {
   // multiples threads
   withContext(Dispatchers.Default) {
      massiveRun2 {
         // confino cada incremento a un contexto de hilo único
         withContext(counterContext) {
            // println(Thread.currentThread()) // Siempre el mismo hilo
            counter2++
         }
      }
   }
   println("Contador = ${counter2.format()}")
}