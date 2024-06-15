package com.jmendezv.coroutineswithdb.seccion_03.leccion_11

/*
* LECCIÓN 11: GESTIÓN DE EXCEPCIONES
*
* */

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Savepoint

const val query1: String = "insert high_priority into departments values ('d100','Security');"
const val query2: String = "insert high_priority into departments values ('d101','Administration');"
const val jdbcEmployeesUrl: String = "jdbc:mysql://localhost:3306/employees"

fun main() {

   val connection: Connection = DriverManager
      .getConnection(jdbcEmployeesUrl, "root", "admin")
   // Deshabilito el modo autocommit para poder agrupar dos operaciones en una transacción
   connection.autoCommit = false
   /*
   * Un 'Savepoint' es un punto dentro de la transacción actual que puede ser referenciado desde
   * el método Connection.rollback.
   *
   * Cuando una transacción se revierte a un punto de salvaguarda, todos los cambions hecho después
   * de ella se deshacen.
   *
   * */
   val save1: Savepoint = connection.setSavepoint("save_01")
   val query1: PreparedStatement = connection.prepareStatement(query1)
   val query2: PreparedStatement = connection.prepareStatement(query2)
   try {
      query1.execute()
      query2.execute()
      connection.commit()
   } catch (e: SQLException) {
      connection.rollback(save1)
   } finally {
      connection.close()
   }
}