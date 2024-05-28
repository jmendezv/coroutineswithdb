package com.jmendezv.coroutineswithdb.seccion_04.leccion_18

import com.jmendezv.coroutineswithdb.seccion_04.leccion_17.Departments
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

fun main() {

   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   val querySource: QuerySource = database.from(Departments)
   val query: Query = querySource.select(Departments.deptNo, Departments.deptName).where {
      (Departments.deptNo eq "d001") or (Departments.deptNo eq "d002")
   }

   query
      .map { row -> Department(row[Departments.deptNo], row[Departments.deptName]?.uppercase()) }
      .filter { it.deptName != null && it.deptName.startsWith("FIN")}
      .sortedByDescending { it.deptNo }
      .forEach(::println)
}