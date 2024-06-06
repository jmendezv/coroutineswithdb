package com.jmendezv.coroutineswithdb.seccion_05.leccion_35

import kotlinx.coroutines.*
import org.ktorm.entity.forEach
import org.ktorm.entity.take
import java.io.*

/*
* Exceptions aggregation *
*
* */

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
   val handler = CoroutineExceptionHandler { _, exception ->
      println("Mi CoroutineExceptionHandler obtuvo la excepción $exception con la excepción suprimida ${exception.suppressed.contentToString()}")
   }
   val job = GlobalScope.launch(handler) {
      val jobEmployees = launch {
         try {
            database.employees.forEach {
               println(it)
               yield()
            }
         } finally {
            throw ArithmeticException() // Segunda excepción porque employees tiene miles de registros
         }
      }
      val jobDepartments = launch {
         database.departments.forEach {
            println(it)
            yield()
         }
         throw IOException() // Primera excepción porque departments tiene pocos registros
      }
      jobEmployees.join()
      jobDepartments.join()
   }
   job.join()
}