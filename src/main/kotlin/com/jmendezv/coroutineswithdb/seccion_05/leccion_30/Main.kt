package com.jmendezv.coroutineswithdb.seccion_05.leccion_30

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
* LECCIÓN 30: LA FUNCIÓN LUNCH VS ASYNC
*
* */

fun main() = runBlocking {
   launch {
      println("****** Employees *******")
      getEmployees()
      println("****** End Employees *******")
   }
   launch {
      println("****** Departments *******")
      getDepartments()
      println("****** End Departments *******")
   }
   println("Main acaba")
}