package io.cloudflight.workshop.adapter.out.persistence.employee

import io.cloudflight.workshop.application.usecase.EmployeeListEntry
import io.cloudflight.workshop.application.usecase.GetEmployeeListPort
import org.springframework.stereotype.Service

@Service
internal class GetEmployeeListPersistenceAdapter(
    private val employeeRepository: EmployeeRepository,
) : GetEmployeeListPort {
    override fun getEmployeeList(): List<EmployeeListEntry> {
        return employeeRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getFilteredEmployeeList(partialName: String): List<EmployeeListEntry> {
        return employeeRepository.findAllByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(
            firstName = partialName,
            lastName = partialName,
        ).map { it.toDTO() }
    }

    private fun Employee.toDTO() = EmployeeListEntry(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
    )
}
