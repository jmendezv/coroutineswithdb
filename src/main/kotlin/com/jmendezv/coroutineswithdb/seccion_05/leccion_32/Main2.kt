package com.jmendezv.coroutineswithdb.seccion_05.leccion_32

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.ktorm.entity.toList

suspend fun simpleSuspendedList(): List<Department> {
   delay(1000) // construyendo la lista...
   return database.departments.toList()
}

fun main() = runBlocking<Unit> {
   simpleSuspendedList().forEach(::println)
}