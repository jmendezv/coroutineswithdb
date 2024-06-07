package com.jmendezv.coroutineswithdb.seccion_05.leccion_36

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.ktorm.entity.update
import java.time.LocalDate

fun updateSalary(id: Int, date: LocalDate) {
   val salary: Salary? = database.salaries.find {
      it.empNo eq id and(
           it.fromDate eq date)
   }
   println(salary)
   salary?.run {
      var newSalary = salary.copy()
      newSalary.salary++
      database.salaries.update(newSalary)
   }
}

val lock = Mutex()
fun main() = runBlocking {
   // multiples threads
   withContext(Dispatchers.Default) {
      repeat(1_000) {
         launch {
            // exclusión mútua
            lock.withLock {
               updateSalary(11_999, LocalDate.of(2_001, 7, 20))
            }
         }
      }
   }
}