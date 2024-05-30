package com.jmendezv.coroutineswithdb.seccion_04.leccion_20

import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.update
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

// UPDATE
fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

  database.delete(Departments) {
     it.deptNo eq "d101"
  }

}