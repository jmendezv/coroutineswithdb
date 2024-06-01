package com.jmendezv.coroutineswithdb.seccion_04.leccion_27

import org.ktorm.database.Database
import org.ktorm.dsl.QueryRowSet
import org.ktorm.entity.forEach
import org.ktorm.entity.sequenceOf
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import org.ktorm.schema.BaseTable
import org.ktorm.schema.varchar

/*
* LECCIÓN 27: ENTIDADES DE CUALQUIER CLASE
*
* */

data class Department(
   val deptNo: String,
   val deptName: String,
)

object Departments : BaseTable<Department>("departments") {
   val deptNo = varchar("dept_no").primaryKey()
   val deptName = varchar("dept_name")

   override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean) = Department(
      deptNo = row[deptNo].orEmpty(),
      deptName = row[deptName].orEmpty()
   )
}

val Database.departments get() = this.sequenceOf(Departments)

fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )
   database.departments.forEach {
      println(it)
   }
}