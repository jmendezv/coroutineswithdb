package com.jmendezv.coroutineswithdb.seccion_04.leccion_25

/*
* LECCIÓN 25: ENTIDADES: AGREGACIONES
*
* */

import org.ktorm.database.Database
import org.ktorm.dsl.like
import org.ktorm.entity.count
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

   val count = database.departments.count {
      it.deptName like "Pro%"
   }

   println(count)
}