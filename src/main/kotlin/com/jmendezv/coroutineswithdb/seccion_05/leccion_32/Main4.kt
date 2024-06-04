package com.jmendezv.coroutineswithdb.seccion_05.leccion_32

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking<Unit> {
   // esto demuestra el hilo principal no esta bloqueado porque ejecuta este blucle mientras recibe flujo
   withTimeoutOrNull(250) {
      for (k in 1..10) {
         println("No estoy bloqueado $k")
         delay(100)
      }
   }
   // Recupera el flujo
   simpleFlow().collect(::println)
}