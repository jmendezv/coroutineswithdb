package com.jmendezv.coroutineswithdb.seccion_05.leccion_31

import com.jmendezv.coroutineswithdb.seccion_04.leccion_25.Department
import com.jmendezv.coroutineswithdb.seccion_04.leccion_25.Departments
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.EntitySequence
import org.ktorm.entity.count
import org.ktorm.entity.forEach
import org.ktorm.entity.sequenceOf
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import java.text.NumberFormat

internal val database: Database = Database.connect(
   url = "jdbc:mysql://localhost:3306/employees",
   user = "root",
   password = "admin",
   // Nivel de mensajes que se muestran por el terminal
   logger = ConsoleLogger(threshold = LogLevel.TRACE)
)

internal val Database.departments: EntitySequence<Department, Departments>
   get() = this.sequenceOf(Departments)
internal val Database.employees: EntitySequence<Employee, Employees>
   get() = this.sequenceOf(Employees)

internal fun getDepartments(): Unit = database.departments.forEach { println(it) }
internal fun getEmployees(): Unit = database.employees.forEach { println(it) }

internal fun getHowManyEmployeeWomen(): Int = database.employees.count {
   it.gender eq "F"
}

internal fun getHowManyEmployeeMen(): Int = database.employees.count {
   it.gender eq "M"
}

internal fun Number.format(): String = NumberFormat.getInstance().format(this)