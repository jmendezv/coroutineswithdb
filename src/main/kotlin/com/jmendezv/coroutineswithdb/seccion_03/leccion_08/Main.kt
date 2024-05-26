package com.jmendezv.coroutineswithdb.seccion_03.leccion_08

/*
* LECCIÓN 08: UNA CONEXIÓN SENCILLA A UNA BASE DE DATOS DE MYSQL
*
* */

/*
* Para restaurar el password si lo has olvidado
* sudo mysqld_safe --skip-grant-tables
* mysql -u root
* UPDATE mysql.user SET authentication_string=PASSWORD("rootpass") WHERE User='root';
* FLUSH PRIVILEGES;
* */


import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

data class Department(val dept_no: String, var dept_name: Int) {
   override fun toString(): String = "[$dept_no, $dept_name]"
}
val query: String = """
   select t.title as 'Job', format(count(e.first_name),0) as 'Total Employees' 
   from employees as e 
   left join titles as t on e.emp_no = t.emp_no 
   where not t.title like 'Senior%' 
   group by t.title 
   having count(e.first_name) > 1000 
   order by count(e.first_name) desc;
""".trimIndent()

fun main() {
   val jdbcUrl: String = "jdbc:mysql://localhost:3306/employees"
   val connection: Connection = DriverManager
      .getConnection(jdbcUrl, "root", "admin")
   // println(connection.isValid(0))
   // the query is only prepared not executed
   // val query: PreparedStatement = connection.prepareStatement("SELECT * FROM departments")
   val query: PreparedStatement = connection.prepareStatement(query)

   // the query is executed and results are fetched
   val result: ResultSet = query.executeQuery()

   // an empty list for holding the results
   val departments: MutableList<Department> = mutableListOf()

   while (result.next()) {

      // getting the value of the id column
      // val id: String = result.getString("dept_no")
      // val name: String = result.getString("dept_name")
      val job: String = result.getString("Job")
      val total: Int = result.getInt("Total Employees")

      departments.add(Department(job, total))
   }

   departments.forEach(::println)
}