package com.jmendezv.coroutineswithdb.seccion_05.leccion_36

import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int

object Salaries: Table<Salary>("salaries") {
   val empNo = int("emp_no").primaryKey().bindTo { it.empNo }
   val salary = int("salary").bindTo { it.salary }
   val fromDate = date("from_date").primaryKey().bindTo { it.fromDate }
   val toDate = date("to_date").bindTo { it.toDate }
}