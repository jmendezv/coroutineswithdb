package com.jmendezv.coroutineswithdb.seccion_05.leccion_29

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
* LECCIÓN 29: TU PRIMERA CORRUTINA
*
* */

fun main() = runBlocking { // this: CoroutineScope
   val job = launch { // Lanza una corrutina y continua
      getDepartments()
      println("launch acaba!") // Se imprime cuando acaba la llamada anterior
   }
   println("Main acaba") // launch no es bloqueante, este mensaje se escribe antes de que acabe la corrutina
}