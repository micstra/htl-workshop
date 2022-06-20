package io.cloudflight.workshop.application.usecase

import org.springframework.stereotype.Service

interface GetEmployeeListUseCase {
    fun getEmployeeList(): List<EmployeeListEntry>
}

@Service
internal class GetEmployeeListService(
    private val getEmployeeListPort: GetEmployeeListPort
) : GetEmployeeListUseCase{
    override fun getEmployeeList(): List<EmployeeListEntry> {
        return getEmployeeListPort.getEmployeeList()
    }
}

interface GetEmployeeListPort {
    fun getEmployeeList(): List<EmployeeListEntry>
}

data class EmployeeListEntry(
    val id: String,
    val firstName: String,
    val lastName: String,
)
