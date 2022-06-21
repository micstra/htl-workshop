package  io.cloudflight.workshop.application.usecase

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class GetEmployeeListUseCaseTest {

    private val getEmployeeListPort: GetEmployeeListPort = mockk(relaxed = true)

    private val getEmployeeListUseCase = GetEmployeeListService(
        getEmployeeListPort = getEmployeeListPort
    )

    @Test
    fun `fetches employees without filtering if no partialName is passed`() {
        getEmployeeListUseCase.getEmployeeList()

        verify(exactly = 1) { getEmployeeListPort.getEmployeeList() }
        verify(exactly = 0) { getEmployeeListPort.getFilteredEmployeeList(any()) }
    }

    @Test
    fun `fetches employees without filtering if partialName is blank string`() {
        getEmployeeListUseCase.getEmployeeList(partialName = "      ")

        verify(exactly = 1) { getEmployeeListPort.getEmployeeList() }
        verify(exactly = 0) { getEmployeeListPort.getFilteredEmployeeList(any()) }
    }

    @Test
    fun `fetches filtered employees if partialName is not null`() {
        getEmployeeListUseCase.getEmployeeList(partialName = "Fra")

        verify(exactly = 1) { getEmployeeListPort.getFilteredEmployeeList(eq("Fra")) }
        verify(exactly = 0) { getEmployeeListPort.getEmployeeList() }
    }

}
