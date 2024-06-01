package com.jmendezv.coroutineswithdb.seccion_05.leccion_28

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
* LECCIÓN 28: ¿QUÉ SON LA CORRUTINAS Y LOS CANALES?
*
* */
fun main() = runBlocking { // this: CoroutineScope
   launch { // launch a new coroutine and continue
      delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
      println("World!") // print after delay
   }
   println("Hello") // main coroutine continues while a previous one is delayed
}