package com.jmendezv.coroutineswithdb.seccion_05.leccion_32

import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Employees : Table<Employee>("employees") {
   val empNo = int("emp_no").primaryKey().bindTo { it.empNo }
   val birthDate = date("birth_date").bindTo { it.birthDate }
   val firstName = varchar("first_name").bindTo { it.firstName }
   val lastName = varchar("last_name").bindTo { it.lastName }
   val gender = varchar("gender").bindTo { it.gender }
   val hireDate = date("hire_date").bindTo { it.hireDate }
}