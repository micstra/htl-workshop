package io.cloudflight.workshop.adapter.out.persistence.employee

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, String>{
    fun findAllByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(firstName: String, lastName: String): List<Employee>
}
