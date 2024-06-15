package com.jmendezv.coroutineswithdb.seccion_04.leccion_22

import org.ktorm.schema.Column
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

/*
* Definición de tabla: Table
*
* */
object Departments : Table<Department>("departments") {
   val deptNo: Column<String> = varchar("dept_no").primaryKey().bindTo { it.deptNo}
   val deptName: Column<String> = varchar("dept_name").bindTo { it.deptName }
}