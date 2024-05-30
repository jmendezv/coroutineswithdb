package com.jmendezv.coroutineswithdb.seccion_04.leccion_21

import com.jmendezv.coroutineswithdb.seccion_04.leccion_17.Departments
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

/*
* LECCIÓN 21: OPERADORES
*
* https://www.ktorm.org/en/operators.html#Operators
*
* */
fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   val querySource: QuerySource = database.from(Departments)
   val query: Query = querySource.select(Departments.deptNo, Departments.deptName).where {
      (Departments.deptNo eq "d001") or (Departments.deptNo eq "d002") and (Departments.deptNo lt "d009")
   }
}