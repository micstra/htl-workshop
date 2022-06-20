package io.cloudflight.workshop.adapter.out.persistence.employee

import io.cloudflight.workshop.config.PersistenceIntegrationTest
import io.cloudflight.workshop.repositories.EmployeeRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@PersistenceIntegrationTest
internal class GetEmployeeListPersistenceAdapterTest(
    @Autowired private val getEmployeeListPersistenceAdapter: GetEmployeeListPersistenceAdapter,
    @Autowired private val employeeRepository: EmployeeRepository,
) {

    @Nested
    inner class GetEmployeeListTests {

        @BeforeEach
        fun setup() {
            employeeRepository.saveAll(
                listOf(
                    EmployeeBuilder.buildEmployee(
                        id = "Hube",
                        firstName = "Franz",
                        lastName = "Huber",
                    ),
                    EmployeeBuilder.buildEmployee(
                        id = "Schm",
                        firstName = "Barbara",
                        lastName = "Schmidt"
                    )
                )
            )
        }

        @Test
        fun `returns all employees`() {
            val employeeList = getEmployeeListPersistenceAdapter.getEmployeeList()

            assertThat(employeeList).isNotNull.hasSize(2)
            assertThat(employeeList.map { it.firstName }).containsExactly("Franz", "Barbara")
        }
    }

}
