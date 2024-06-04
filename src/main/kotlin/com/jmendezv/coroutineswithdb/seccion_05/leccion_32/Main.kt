package com.jmendezv.coroutineswithdb.seccion_05.leccion_32

import org.ktorm.entity.forEach
import org.ktorm.entity.toList

/*
* LECCIÓN 32: FLUJO ASÍNCRONO
*
* */

fun simpleList(): List<Department> = database.departments.toList()
fun simpleSeq(): Sequence<Department> = sequence { // sequence builder
   database.departments.forEach {
      // Thread.sleep(100)
      yield(it)
   }
}

fun main() {
   println("simpleList()")
   simpleList().forEach(::println)
   println("simpleSeq()")
   simpleSeq().forEach(::println)
}