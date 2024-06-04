package com.jmendezv.coroutineswithdb.seccion_05.leccion_32

import kotlinx.coroutines.flow.*
import org.ktorm.entity.toList

suspend fun main() {
   println("*****")
   // Así se convierte un rango en un flujo
   (1..3).asFlow().collect(::println)
   println("*****")
   // Así se convierte una secuencia a lista y de lista a flujo
   database.departments.toList().asFlow().collect(::println)
   println("*****")
   // Así se trata un flujo con funciones terminales como take y map que transforman
   // una lista de departamentos en un lista menguada de parejas de cadenas
   database.departments.toList().asFlow()
      .filter { it.deptNo < "d005" }
      .take(3)
      .map {
         Pair(it.deptNo, it.deptName)
      }
      .collect(::println)
}