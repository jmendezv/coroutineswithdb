package com.jmendezv.coroutineswithdb.seccion_04.leccion_26

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   val departamento: Department? = database.departments
      .find { it.deptNo eq "d101" }
   departamento?.delete()

   println("$departamento ha sido eliminado.")
}