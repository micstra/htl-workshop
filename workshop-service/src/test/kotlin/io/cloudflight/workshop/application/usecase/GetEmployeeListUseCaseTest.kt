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
    fun `fetches data from persistence port`() {
        getEmployeeListUseCase.getEmployeeList()

        verify(exactly = 1) { getEmployeeListPort.getEmployeeList() }
    }

}
