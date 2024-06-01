package com.jmendezv.coroutineswithdb.seccion_05.leccion_29

import com.jmendezv.coroutineswithdb.seccion_04.leccion_25.Departments
import org.ktorm.database.Database
import org.ktorm.entity.forEach
import org.ktorm.entity.sequenceOf
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

internal val Database.departments
   get() = this.sequenceOf(Departments)

internal val database = Database.connect(
   url = "jdbc:mysql://localhost:3306/employees",
   user = "root",
   password = "admin",
   logger = ConsoleLogger(threshold = LogLevel.ERROR)
)

internal fun getDepartments(): Unit = database.departments.forEach { println(it) }