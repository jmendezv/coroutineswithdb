package com.jmendezv.coroutineswithdb.seccion_04.leccion_23

/*
* LECCIÓN 23: ENTIDADES: CONSULTAS
*
* */

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

val Database.departments
   get() = this.sequenceOf(Departments)

fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   val department = database.departments.find { it.deptNo eq "d001" }
   println(department)
}