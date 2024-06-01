package com.jmendezv.coroutineswithdb.seccion_04.leccion_26

import org.ktorm.database.Database
import org.ktorm.entity.update
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   database.departments.update(Department {
      deptNo = "d101"
      deptName = "Departamento 101 modificado"
   })

}