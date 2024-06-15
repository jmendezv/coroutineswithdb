package com.jmendezv.coroutineswithdb.seccion_04.leccion_24

/*
* LECCIÓN 24: ENTIDADES: SECUENCIAS
*
* */

import org.ktorm.database.Database
import org.ktorm.dsl.gt
import org.ktorm.entity.*
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

   for (department in database.departments) {
      println(department)
   }

   val departamentos: List<Department> = database
      .departments.filter {
      it.deptNo gt "d005"
   }.sortedBy { it.deptName }.toList()

   val nombres: List<String> = database.departments
      .map {
      it.deptName }

   val nombres2: String = database.departments
      .joinToString(separator = ":") { it.deptName }

   println(nombres2)
}