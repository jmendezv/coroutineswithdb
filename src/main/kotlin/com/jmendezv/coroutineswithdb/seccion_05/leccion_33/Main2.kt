package com.jmendezv.coroutineswithdb.seccion_05.leccion_33

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.ktorm.entity.forEach

fun main() = runBlocking {
   val channel = Channel<Department>()
   launch {
      database.departments.forEach {
         channel.send(it)
      }
      channel.close()
   }
   // Tomamos los 5 primeros
   repeat(5) {
      println(channel.receive())
   }
   println("Main acaba!")
}