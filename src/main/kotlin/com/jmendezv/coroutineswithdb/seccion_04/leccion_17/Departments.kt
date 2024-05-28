package com.jmendezv.coroutineswithdb.seccion_04.leccion_17

import org.ktorm.schema.*

/*
* Mapping to departments: Schema
*
* create table departments (
* dept_no char(4) not null primary,
* dept_name varchar(128) not null);
*
* */
object Departments: Table<Nothing>("departments") {
   val deptNo = varchar("dept_no").primaryKey()
   val deptName = varchar("dept_name")
}

/*
*
* Tres esquemas iguales, pero con nombres de tablas diferentes.
*
* */
sealed class Employees(tableName: String) : Table<Nothing>(tableName) {
   val id = int("id").primaryKey()
   val name = varchar("name")
   val job = varchar("job")
   val managerId = int("manager_id")
   val hireDate = date("hire_date")
   val salary = long("salary")
   val departmentId = int("department_id")
}

object RegularEmployees : Employees("t_regular_employee")

object FormerEmployees : Employees("t_former_employee")
