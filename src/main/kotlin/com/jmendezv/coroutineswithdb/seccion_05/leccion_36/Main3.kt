package com.jmendezv.coroutineswithdb.seccion_05.leccion_36

import kotlinx.coroutines.*
import kotlin.system.*

/*
* En la práctica, el confinamiento del hilo se realiza en grandes piezas de lógica.
* El siguiente ejemplo lo hace así: para empezar, ejecuta cada rutina en el contexto de un solo hilo.
*
* */
suspend fun massiveRun3(action: suspend () -> Unit) {
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

val counterContext2 = newSingleThreadContext("CounterContext")
var counter3 = 0

fun main() = runBlocking {
   // un único thread
   withContext(counterContext2) {
      massiveRun3 {
         println(Thread.currentThread()) // Siempre el mismo hilo
         counter3++
      }
   }
   println("Contador = ${counter3.format()}")
}