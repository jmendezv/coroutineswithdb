package com.jmendezv.coroutineswithdb.seccion_04.leccion_20

import org.ktorm.database.Database
import org.ktorm.database.asIterable
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import org.ktorm.support.mysql.MySqlDialect
import java.sql.PreparedStatement

// Acceso a SQL nativo desde Ktorm

fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      dialect = MySqlDialect(),
      logger = ConsoleLogger(threshold = LogLevel.INFO)
   )

   val names: List<String> = database.useConnection { conn ->
      val sql = """
        select distinct first_name from employees
        where emp_no > ?
        order by first_name
        limit 10;
    """

      conn.prepareStatement(sql).use { statement: PreparedStatement ->
         statement.setInt(1, 1)
         statement.executeQuery().asIterable().map { it.getString(1) }
      }
   }

   names.forEach(::println)
}
