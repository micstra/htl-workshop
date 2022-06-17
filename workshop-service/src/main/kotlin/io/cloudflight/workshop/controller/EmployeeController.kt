package io.cloudflight.workshop.controller

import io.cloudflight.workshop.api.EmployeeApiDelegate
import io.cloudflight.workshop.api.model.ReadEmployeeDto
import io.cloudflight.workshop.model.Employee
import io.cloudflight.workshop.repositories.EmployeeRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EmployeeController(
        private val employeeRepository: EmployeeRepository
) : EmployeeApiDelegate {

    override fun findAllEmployees(): ResponseEntity<List<ReadEmployeeDto>> {
        return ResponseEntity.ok(employeeRepository.findAll().map { it.toDto() })
    }

    private fun Employee.toDto(): ReadEmployeeDto {
        return ReadEmployeeDto().also {
            it.id = id
            it.firstName = firstName
            it.lastName = lastName
        }
    }

}