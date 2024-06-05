package com.jmendezv.coroutineswithdb.seccion_05.leccion_33

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import org.ktorm.entity.forEach

/*
* FAN-IN
*
* La carga de trabajo de varios productrores se entrega a un consumidores:
* 1+ Producer -> 1 Consumer
*
* */
fun main() = runBlocking {
   val channel = Channel<String>()
   launch {
      database.departments.forEach {
         sendString(channel, it.toString(), 0L)
      }
   }
   launch {
      database.employees.forEach {
         sendString(channel, it.toString(), 0L)
      }
   }
   repeat(10) { // recibe los 10 primeros
      println(channel.receive())
   }
   coroutineContext.cancelChildren() // cancela todos los hijos
}

suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
   delay(time)
   channel.send(s)
}