package com.jmendezv.coroutineswithdb.seccion_04.leccion_20

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

/*
* LECCIÓN 20: MANIPULACIÓN DE DATOS
*
* */

// INSERT
fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   val t: Int = database.insert(Departments) {
      set(it.deptNo, "d101")
      set(it.deptName, "Riesgos Laborales")
   }

   /*
   database.batchInsert(Departments) {
      item {
         set(it.deptNo, "d101")
         set(it.deptName, "Departamento 101")
      }
      item {
         set(it.deptNo, "d102")
         set(it.deptName, "Departamento 102")
      }
   } */

}