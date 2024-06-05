package com.jmendezv.coroutineswithdb.seccion_05.leccion_34

import kotlinx.coroutines.*
import org.ktorm.entity.forEachIndexed

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
      val gestorDeExcepciones = CoroutineExceptionHandler { _, exception ->
         println("$exception Gestionada por un CoroutineExceptionHandler personalizado")
      }
      val job = GlobalScope.launch(gestorDeExcepciones) { // root coroutine with launch
         database.departments.forEachIndexed() { index: Int, department: Department ->
            // Thread.defaultUncaughtExceptionHandler gestiona la excepción
            if (index > 5) throw IndexOutOfBoundsException("Índice desbordado :(")
            println("$index $department")
         }
      }
      job.join()
      println("El join no se lleva a cabo")
      val deferred = GlobalScope.async(gestorDeExcepciones) { // root coroutine with async
         database.employees.forEachIndexed() { index: Int, employee: Employee ->
            // La función wait() gestiona la excepción
            if (index > 5) throw ArithmeticException("Error de cálculo :(")
            println("$index $employee")
         }
      }
      try {
         deferred.await()
         println("Aquí no llega porque salta una excepción en la línea anterior")
      } catch (e: ArithmeticException) {
         println("ArithmeticException capturada: ${e.message}")
      }
   }