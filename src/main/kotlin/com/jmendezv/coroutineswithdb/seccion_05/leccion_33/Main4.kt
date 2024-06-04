package com.jmendezv.coroutineswithdb.seccion_05.leccion_33

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import org.ktorm.entity.forEach

fun CoroutineScope.produceDepartments2(): ReceiveChannel<Department> = produce {
   database.departments.forEach {
      send(it)
      delay(100)
   }
}

fun CoroutineScope.launchDptProcessor(id: Int, channel: ReceiveChannel<Department>) = launch {
   for (dpt in channel) {
      when (id) {
         0 -> println("Procesador #$id gestionando ${dpt.deptName}")
         1 -> println("Procesador #$id gestionando ${dpt.deptNo}")
         2 -> println("Procesador #$id gestionando $dpt")
         3 -> println("Procesador #$id gestionando ${Pair(dpt.deptNo, dpt.deptName)}")
         else -> println("El procesador #$id no admite trabajo...")
      }
   }
}

fun main() = runBlocking {
   val dptProducer = produceDepartments2()
   repeat(5) { launchDptProcessor(it, dptProducer) }
   delay(5_000)
   dptProducer.cancel() // cancel producer coroutine and thus kill them all
   println("Main ends")
}