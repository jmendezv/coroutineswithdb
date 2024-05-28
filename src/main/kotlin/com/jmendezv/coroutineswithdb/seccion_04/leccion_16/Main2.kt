package com.jmendezv.coroutineswithdb.seccion_04.leccion_16

import org.ktorm.database.Database
import org.ktorm.database.TransactionIsolation
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

fun main() {
   val database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG),
      alwaysQuoteIdentifiers = true,
      generateSqlInUpperCase = true
   )

   val transactionManager = database.transactionManager
   val transaction = transactionManager.newTransaction(isolation = TransactionIsolation.READ_COMMITTED)

   try {
      // Modificación de las tablas
      transaction.commit()
   } catch (e: Throwable) {
      transaction.rollback()
   } finally {
      transaction.close()
   }
}