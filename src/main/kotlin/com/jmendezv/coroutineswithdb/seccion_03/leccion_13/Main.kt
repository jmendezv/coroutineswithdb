package com.jmendezv.coroutineswithdb.seccion_03.leccion_13

/*
* LECCIÓN 13: STORED PROCEDURES
*
* */

import java.sql.*

const val jdbcEmployeesUrl: String = "jdbc:mysql://localhost:3306/employees"
const val storedProcedure: String = "{call findDpt(?, ?)}"

fun main() {

   val connection: Connection = DriverManager
      .getConnection(jdbcEmployeesUrl, "root", "admin")

   val callableStatement: CallableStatement = connection.prepareCall(storedProcedure)
   // IN parameter
   callableStatement.setString(1, "d002")
   // Register of OUT parameters
   callableStatement.registerOutParameter(2, Types.VARCHAR)

   try {
      callableStatement.execute()
      val deptName: String = callableStatement.getString(2)
      println(deptName)
   } catch (e: SQLException) {
      println(e.message)
   } finally {
      connection.close()
   }
}