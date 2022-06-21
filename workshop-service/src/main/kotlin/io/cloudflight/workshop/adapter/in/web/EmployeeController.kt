package io.cloudflight.workshop.adapter.`in`.web

import io.cloudflight.workshop.api.EmployeeApiDelegate
import io.cloudflight.workshop.api.model.EmployeeListEntryDTO
import io.cloudflight.workshop.api.model.EmployeeWithTasksDTO
import io.cloudflight.workshop.api.model.TaskDTO
import io.cloudflight.workshop.api.model.UpdateEmployeeDTO
import io.cloudflight.workshop.application.usecase.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EmployeeController(
        private val getEmployeeListUseCase: GetEmployeeListUseCase,
        private val getEmployeeDetailsUseCase: GetEmployeeDetailsUseCase,
        private val saveOrUpdateEmployeeUseCase: SaveOrUpdateEmployeeUseCase
) : EmployeeApiDelegate {

    override fun listEmployees(): ResponseEntity<List<EmployeeListEntryDTO>> {
        return ResponseEntity.ok(
                getEmployeeListUseCase.getEmployeeList()
                        .map { it.toDTO() }
        )
    }

    override fun listFilteredEmployees(partialName: String?): ResponseEntity<List<EmployeeListEntryDTO>> {
        return ResponseEntity.ok(
            getEmployeeListUseCase.getEmployeeList(partialName = partialName)
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

    override fun findEmployeeDetails(id: String): ResponseEntity<EmployeeWithTasksDTO> {
        return ResponseEntity.ok(
                getEmployeeDetailsUseCase.findEmployeeDetails(id).toDTO()
        )
    }

    private fun EmployeeDetails.toDTO(): EmployeeWithTasksDTO {
        return EmployeeWithTasksDTO().also {
            it.id = employeeInfo.id
            it.firstName = employeeInfo.firstName
            it.lastName = employeeInfo.lastName

            it.tasks = tasks.map { taskInfo ->
                TaskDTO().also { taskDto ->
                    taskDto.id = taskInfo.id
                    taskDto.description = taskInfo.description
                    taskDto.finished = taskInfo.finishedAt
                    taskDto.hoursWorked = taskInfo.hoursWorked
                }
            }
        }
    }

    override fun saveOrUpdateEmployee(id: String, updateEmployeeDTO: UpdateEmployeeDTO): ResponseEntity<Void> {
        saveOrUpdateEmployeeUseCase.saveOrUpdate(id, updateEmployeeDTO.firstName, updateEmployeeDTO.lastName)
        return ResponseEntity.ok().build()
    }
}
