package com.jmendezv.coroutineswithdb.seccion_04.leccion_15

import com.jmendezv.coroutineswithdb.seccion_04.leccion_17.Departments
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

/*
* LECCIÓN 15: UNA CONEXIÓN SENCILLA A UNA BASE DE DATOS
*
* */
fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG))

   for (row in database.from(Departments).select()) {
      println(row[Departments.deptName])
   }
}