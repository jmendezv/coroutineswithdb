package com.jmendezv.coroutineswithdb.seccion_05.leccion_30


import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.system.measureTimeMillis

/*
* LECCIÓN 30: LA FUNCIÓN LUNCH VS ASYNC
*
* Los constructores runBlocking y coroutineScope pueden parecer similares porque ambos esperan a que se completen
* su bloque y todos sus elementos secundarios.
*
* La principal diferencia es que el método runBlocking bloquea el hilo actual en espera, mientras que coroutineScope
* simplemente se suspende, liberando el hilo subyacente para otros usos.
*
* Debido a esa diferencia, runBlocking es una función regular y coroutineScope es una función de suspensión.
*
* */

suspend fun main() = coroutineScope {
   println("En main()")
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