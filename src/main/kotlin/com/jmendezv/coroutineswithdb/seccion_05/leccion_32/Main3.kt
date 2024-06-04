package com.jmendezv.coroutineswithdb.seccion_05.leccion_32

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.ktorm.entity.forEach

internal fun simpleFlow(): Flow<Department> = flow { // flow builder
   println("Flujo iniciado.")
   database.departments.forEach {
      emit(it)
      delay(100)
   }
}

fun main() = runBlocking<Unit> {
   // esto demuestra el hilo principal no esta bloqueado porque ejecuta este blucle mientras recibe flujo
   launch {
      for (k in 1..10) {
         println("No estoy bloqueado $k")
         delay(100)
      }
   }
   // Recupera el flujo
   simpleFlow().collect(::println)
}