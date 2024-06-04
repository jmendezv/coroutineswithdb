package com.jmendezv.coroutineswithdb.seccion_05.leccion_33

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import org.ktorm.entity.forEach

val channel = Channel<Department>()

fun CoroutineScope.produceDepartments(): ReceiveChannel<Department> = produce {
   database.departments.forEach {
      channel.send(it)
   }
}

fun main() = runBlocking {
   val dpts = produceDepartments()
   dpts.consumeEach { println(it) }
   println("Done!")
}