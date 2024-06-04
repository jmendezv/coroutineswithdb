package com.jmendezv.coroutineswithdb.seccion_05.leccion_31

/*
* LECCIÓN 31: COROUTINE CONTEXT Y DISPATCHERS
*
* */

import kotlinx.coroutines.*
import kotlinx.coroutines.async
import kotlin.system.measureTimeMillis

/*
* Desde Android sería algo así sin el método runBlocking() ni coroutineScope().
*
* Simplemente creando un CoroutineScope con el dispatcher de entradas y salidas.
*
* */

suspend fun main() {
   println("En main()")
   val job: Job = CoroutineScope(Dispatchers.IO).launch {
      val time: Long = measureTimeMillis {
         println("Lanzando primera corrutina...")
         val women: Deferred<Int> = async { getHowManyEmployeeWomen() }
         println("Lanzando segunda corrutina...")
         val men: Deferred<Int> = async { getHowManyEmployeeMen() }
         try {
            val totalMujeres = women.await()
            val totalHombres = men.await()
            println("Total mujeres: ${totalMujeres.format()}")
            println("Total hombres: ${totalHombres.format()}")
            println("Total registros: ${(totalMujeres + totalHombres).format()}")
         } catch (e: Exception) {
            println(e.message)
         }
      }
      println("Completado en ${time.format()} ms.")
   }
   job.join() // Esperamos a que acaben las corrutinas hijas
}