package com.jmendezv.coroutineswithdb.seccion_05.leccion_31

import org.ktorm.entity.Entity
import java.time.LocalDate

/*
* Definicion de entidad: Entity
*
* */
interface Employee : Entity<Employee> {
   companion object : Entity.Factory<Employee>()
   val empNo: Int
   val birthDate: LocalDate
   val firstName: String
   val lastName: String
   val gender: String /* Try Enum here? */
   val hireDate: LocalDate
}