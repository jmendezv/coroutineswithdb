package com.jmendezv.coroutineswithdb.seccion_04.leccion_16

import com.jmendezv.coroutineswithdb.seccion_04.leccion_17.Departments
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.select
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

/*
* LECCIÓN 16: TRANSACCIONES
*
* */
fun main() {
   val database: Database = Database.connect(
      url = "jdbc:mysql://localhost:3306/employees",
      user = "root",
      password = "admin",
      logger = ConsoleLogger(threshold = LogLevel.DEBUG),
      alwaysQuoteIdentifiers = true,
      generateSqlInUpperCase = true
   )

   // Guardamos el número total de registros inicial
   val totalRecsInDepartments = database.from(Departments).select().totalRecordsInAllPages

   try {
      database.useTransaction { transaction ->
         // INSERT INTO departments ...
         database.insert(Departments) { departments ->
            set(departments.deptNo, "d100")
            set(departments.deptName, "Gestion y Control")
         }
         // Confirma se ha añadido un nuevo registro
         assert(database.from(Departments).select().totalRecordsInAllPages == totalRecsInDepartments + 1)
         // Lanzamos una excepción y por lo tanto Ktorm debe hacer un Rollback
         throw IllegalAccessException()
      }

   } catch (e: IllegalAccessException) {
      // confirma que sigue habiendo el mismo número de registros que habia al inicio
      assert(database.from(Departments).select().totalRecordsInAllPages == totalRecsInDepartments)
   }
}