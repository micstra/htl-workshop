package io.cloudflight.workshop.application.usecase

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@Service
class GetEmployeeDetailsUseCase(
        private val getEmployeeDetailsPort: GetEmployeeDetailsPort
) {

    fun findEmployeeDetails(id: String): EmployeeDetails {
        return getEmployeeDetailsPort.getEmployeeDetails(id) ?: throw NoSuchEmployeeException(id)
    }

}

class NoSuchEmployeeException(id: String): ResponseStatusException(HttpStatus.NOT_FOUND, "No such employee with id $id")

interface GetEmployeeDetailsPort {
    fun getEmployeeDetails(id: String): EmployeeDetails?
}

data class EmployeeDetails(
        val employeeInfo: EmployeeInfo,
        val tasks: List<TaskInfo>
)

data class EmployeeInfo(
        val id: String,
        val firstName: String,
        val lastName: String
) {
    fun withTasks(tasks: List<TaskInfo>): EmployeeDetails {
        return EmployeeDetails(this, tasks)
    }
}

data class TaskInfo(
        val id: Int,
        val description: String,
        val finishedAt: LocalDate?,
        val hoursWorked: Int
)