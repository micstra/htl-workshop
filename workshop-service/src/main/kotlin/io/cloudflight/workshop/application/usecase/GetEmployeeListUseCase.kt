package io.cloudflight.workshop.application.usecase

import org.springframework.stereotype.Service

interface GetEmployeeListUseCase {
    fun getEmployeeList(partialName: String? = null): List<EmployeeListEntry>
}

@Service
internal class GetEmployeeListService(
    private val getEmployeeListPort: GetEmployeeListPort
) : GetEmployeeListUseCase {
    override fun getEmployeeList(partialName: String?): List<EmployeeListEntry> {
        return if (partialName.isNullOrBlank()) {
            getEmployeeListPort.getEmployeeList()
        } else {
            getEmployeeListPort.getFilteredEmployeeList(partialName = partialName)
        }
    }
}

interface GetEmployeeListPort {
    fun getEmployeeList(): List<EmployeeListEntry>
    fun getFilteredEmployeeList(partialName: String): List<EmployeeListEntry>
}

data class EmployeeListEntry(
    val id: String,
    val firstName: String,
    val lastName: String,
)
