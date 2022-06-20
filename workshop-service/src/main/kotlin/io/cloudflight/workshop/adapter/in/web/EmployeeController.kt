package io.cloudflight.workshop.adapter.`in`.web

import io.cloudflight.workshop.api.EmployeeApiDelegate
import io.cloudflight.workshop.api.model.EmployeeListEntryDTO
import io.cloudflight.workshop.application.usecase.EmployeeListEntry
import io.cloudflight.workshop.application.usecase.GetEmployeeListUseCase
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EmployeeController(
    private val getEmployeeListUseCase: GetEmployeeListUseCase,
) : EmployeeApiDelegate {

    override fun listEmployees(): ResponseEntity<List<EmployeeListEntryDTO>> {
        return ResponseEntity.ok(
            getEmployeeListUseCase.getEmployeeList()
                .map { it.toDTO() }
        )
    }

    private fun EmployeeListEntry.toDTO(): EmployeeListEntryDTO {
        return EmployeeListEntryDTO().also {
            it.id = id
            it.firstName = firstName
            it.lastName = lastName
        }
    }

}
