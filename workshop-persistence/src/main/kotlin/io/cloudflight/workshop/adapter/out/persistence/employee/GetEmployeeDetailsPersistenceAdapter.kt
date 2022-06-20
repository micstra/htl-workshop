package io.cloudflight.workshop.adapter.out.persistence.employee

import io.cloudflight.workshop.adapter.out.persistence.task.Task
import io.cloudflight.workshop.adapter.out.persistence.task.TaskRepository
import io.cloudflight.workshop.application.usecase.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
internal class GetEmployeeDetailsPersistenceAdapter(
        private val employeeRepository: EmployeeRepository,
        private val taskRepository: TaskRepository
) : GetEmployeeDetailsPort {

    override fun getEmployeeDetails(id: String): EmployeeDetails? {
        val employee = employeeRepository.findByIdOrNull(id) ?: return null
        val tasksOfEmployee = taskRepository.findAllByEmployeeId(id)

        return employee.toInfo().withTasks(tasksOfEmployee.map { it.toTaskInfo() })
    }


    private fun Employee.toInfo(): EmployeeInfo {
        return EmployeeInfo(
                id = id,
                firstName = firstName,
                lastName = lastName
        )
    }

    private fun Task.toTaskInfo(): TaskInfo {
        return TaskInfo(
                id = id,
                description = description,
                finishedAt = finishedAt,
                hoursWorked = hoursWorked
        )
    }


}