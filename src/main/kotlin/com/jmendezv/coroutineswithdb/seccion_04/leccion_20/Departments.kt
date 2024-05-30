package com.jmendezv.coroutineswithdb.seccion_04.leccion_20

import org.ktorm.schema.Table
import org.ktorm.schema.varchar

/*
* Definición de tabla: Table
*
* */
object Departments : Table<Department>("departments") {
   val deptNo = varchar("dept_no").primaryKey().bindTo { it.deptNo}
   val deptName = varchar("dept_name").bindTo { it.deptName }
}