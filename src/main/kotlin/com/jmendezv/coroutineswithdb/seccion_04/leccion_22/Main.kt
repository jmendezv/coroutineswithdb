package com.jmendezv.coroutineswithdb.seccion_04.leccion_22

import org.ktorm.entity.Entity

/*
* LECCIÓN 22: ENTIDADES: INTRODUCCIÓN
*
* Estrategia de nomenclatura: se recomienda nombrar las clases de entidad con nombres singulares
* y los objetos de la tabla con nombres plurales (p. ej., Empleado/Empleados, Departamento/Departamentos).
*
* */
fun main() {
   val department1: Entity<Department> = Entity.create<Department>()
   val department2 = Department()
   val department3 = Department {
      deptNo = "d100"
      deptName = "Special Unit"
   }
}