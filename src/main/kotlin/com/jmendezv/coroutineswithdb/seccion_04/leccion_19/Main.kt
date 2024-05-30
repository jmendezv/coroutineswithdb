package com.jmendezv.coroutineswithdb.seccion_04.leccion_19

import com.jmendezv.coroutineswithdb.seccion_04.leccion_17.Departments
import com.jmendezv.coroutineswithdb.seccion_04.leccion_17.Departments.primaryKey
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar

/*
* LECCIÓN 19: UNIONES
*
* */

// Clave primaria múltiple
object Titles : Table<Nothing>("titles") {
   val empNo = int("emp_no").primaryKey()
   val title = varchar("title").primaryKey()
   val fromDate = date("from_date").primaryKey()
   val toDate = date("to_date")
}

fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   val query = database
      .from(Departments)
      .select(Departments.deptNo)
      .union(
         database.from(Titles).selectDistinct(Titles.empNo)
      )
      .orderBy(Departments.deptNo.desc())

   query.forEach { row ->
      println(row[Departments.deptNo])
   }
}