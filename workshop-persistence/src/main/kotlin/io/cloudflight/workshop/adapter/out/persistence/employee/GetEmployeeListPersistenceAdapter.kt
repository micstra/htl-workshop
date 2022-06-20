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
            .map {
                EmployeeListEntry(
                    id = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                )
            }
    }
}
