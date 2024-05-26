package com.jmendezv.coroutineswithdb.seccion_03.leccion_10

/*
* LECCIÓN 10: TRANSACCIONES
*
* */


import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

val query: String = """
   insert into departments values ("d100","Security");
""".trimIndent()

fun main() {
   val jdbcUrl: String = "jdbc:mysql://localhost:3306/employees"
   val connection: Connection = DriverManager
      .getConnection(jdbcUrl, "root", "admin")

   // The way to allow two or more statements to be grouped into a transaction is to disable the auto-commit mode.
   connection.autoCommit = false
   val query: PreparedStatement = connection.prepareStatement(query)
   query.execute()
   // connection.commit()
   connection.rollback()
   connection.close()
}