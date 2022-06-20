package io.cloudflight.workshop.repositories

import io.cloudflight.workshop.adapter.out.persistence.employee.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, String>{
}
