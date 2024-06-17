package com.jmendezv.coroutineswithdb.seccion_04.leccion_20

import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import org.ktorm.support.mysql.MySqlDialect
import org.ktorm.support.mysql.insertOrUpdate
import org.ktorm.support.mysql.toUpperCase

/*
        <!-- https://mvnrepository.com/artifact/org.ktorm/ktorm-support-mysql -->
        <dependency>
            <groupId>org.ktorm</groupId>
            <artifactId>ktorm-support-mysql</artifactId>
            <version>4.0.0</version>
        </dependency>
* */

// INSERT O UPDATE
fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      dialect = MySqlDialect(),
      logger = ConsoleLogger(threshold = LogLevel.DEBUG)
   )

   val t: Int = database.insertOrUpdate(Departments) {
      set(it.deptNo, "d101")
      set(it.deptName, "Riesgos Laborales")
      onDuplicateKey {
         set(it.deptName, it.deptName.toUpperCase())
      }
   }

}