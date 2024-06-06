package com.jmendezv.coroutineswithdb.seccion_05.leccion_35

import kotlinx.coroutines.*
import org.ktorm.entity.forEach
import org.ktorm.entity.forEachIndexed

/*
* Supervision, ante un error o excepción, la propaga sólo hacia abajo
*
* */

fun main() = runBlocking {

   val supervisor = SupervisorJob()
   with(CoroutineScope(coroutineContext + supervisor)) {
      // Con un gestor de excepciones propio ignoramos la excepción
      val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
      // val firstChild = launch() {
         database.departments.forEachIndexed { index: Int, department: Department ->
            println(department)
            yield()
            if (index > 5) {
               println("El primer hijo falla")
               throw AssertionError("El primer hijo se cancela")
            }
         }
      }
      val secondChild = launch {
         try {
            database.employees.forEach {
               println(it)
               yield()
            }
         }
         catch (e: Exception) {
            println("El segundo hijo se ha cancelado $e automaticamente después de que el supervisor se cancelar")
         }
      }
      delay(5_000)
      println("Cancelando el supervisor")
      supervisor.cancel()
   }

}