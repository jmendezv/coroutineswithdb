package com.jmendezv.coroutineswithdb.seccion_05.leccion_36

import org.ktorm.entity.Entity
import java.time.LocalDate

interface Salary: Entity<Salary> {
   companion object: Entity.Factory<Salary>()
   val empNo: Int
   var salary: Int
   val fromDate: LocalDate
   val toDate: LocalDate
}