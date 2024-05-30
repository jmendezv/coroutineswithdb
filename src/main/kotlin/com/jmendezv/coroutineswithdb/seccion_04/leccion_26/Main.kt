package com.jmendezv.coroutineswithdb.seccion_04.leccion_26

/*
* LECCIÓN 26: ENTIDADES: MANIPULACIÓN
*
* */
import org.ktorm.database.Database
import org.ktorm.entity.EntitySequence
import org.ktorm.entity.add
import org.ktorm.entity.sequenceOf
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

val Database.departments: EntitySequence<Department, Departments>
   get() = this.sequenceOf(Departments)

fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   database.departments.add(Department {
      deptNo = "d101"
      deptName = "Departamento 101"
   })
}