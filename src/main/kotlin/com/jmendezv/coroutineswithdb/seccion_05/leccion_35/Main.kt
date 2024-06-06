package com.jmendezv.coroutineswithdb.seccion_05.leccion_35

/*
* LECCIÓN 35: CANCELACIÓN Y TIMEOUT DE UNA CORRUTINA
*
* yield(): cede el hilo del despachador de la corrutina actual a otra corrutina
* en el mismo despachador por si lo necesita.
*
* */
import kotlinx.coroutines.*
import org.ktorm.entity.forEach


/*
* Cancelación del hijo pero el padre sigue activo
*
* */

fun main() = runBlocking {

   val job = launch {
      val hijo = launch {
         try {
           database.departments.forEach {
              println(it)
              yield()
           }
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