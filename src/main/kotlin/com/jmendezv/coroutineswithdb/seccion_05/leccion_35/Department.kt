package com.jmendezv.coroutineswithdb.seccion_05.leccion_35

import org.ktorm.entity.Entity

/*
* Definicion de entidad: Entity
*
* */
interface Department: Entity<Department> {
   companion object : Entity.Factory<Department>()
   var deptNo: String
   var deptName: String
}