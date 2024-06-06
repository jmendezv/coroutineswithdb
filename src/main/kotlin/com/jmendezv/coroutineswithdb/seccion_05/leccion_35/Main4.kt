package com.jmendezv.coroutineswithdb.seccion_05.leccion_35

import kotlinx.coroutines.*
import org.ktorm.entity.forEach
import org.ktorm.entity.forEachIndexed


/*
* Supervision scope: propaga la cancelación en una sola dirección y cancela todos sus hijos solo si falla él mismo.
* También espera a todos los hijos antes de completarse, tal como lo hace coroutineScope.
*
* */

fun main() = runBlocking {
   try {
      supervisorScope {
         // Con un gestor de excepciones propio ignoramos la excepción
         //val firstChild = launch(CoroutineExceptionHandler { _, _ -> }) {
         val firstChild = launch {
            database.departments.forEachIndexed { index: Int, department: Department ->
               println(department)
               yield()
               if (index > 5) {
                  println("The first child is failing")
                  throw AssertionError("The first child is cancelled")
               }
            }
         } // Primer launch acaba
         val secondChild = launch {
            try {
               database.employees.forEach {
                  println(it)
                  yield()
               }
            } catch (e: Exception) {
               println("El segundo hijo se ha cancelado $e automaticamente después de que el supervisor se cancelar")
            }
         } // Segundo launch acaba
         delay(5_000)
         cancel("Cancelando el supervisor")
      }
   }
   catch (t: Throwable) {
      t.printStackTrace()
   }
   finally {
      println("supervisor scope acaba")
   }
}