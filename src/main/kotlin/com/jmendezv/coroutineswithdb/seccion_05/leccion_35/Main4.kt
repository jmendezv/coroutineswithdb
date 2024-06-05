package com.jmendezv.coroutineswithdb.seccion_05.leccion_35

import kotlinx.coroutines.*


/*
* Supervision scope
*
* */
fun main() = runBlocking {

   val job = launch {
      val hijo = launch {
         try {
            delay(Long.MAX_VALUE)
         } finally {
            println("La corrutina hijo se ha cancelado")
         }
      }
      yield()
      println("Cancelando hijo")
      hijo.cancel()
      hijo.join()
      yield()
      println("El padre no esta cancelado")
   }
   job.join()
   println("Main acaba")
}