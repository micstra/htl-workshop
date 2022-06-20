package io.cloudflight.workshop.adapter.out.persistence.employee

import io.cloudflight.workshop.config.PersistenceIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@PersistenceIntegrationTest
internal class GetEmployeeListPersistenceAdapterTest(
    @Autowired private val getEmployeeListPersistenceAdapter: GetEmployeeListPersistenceAdapter,
    @Autowired private val employeeRepository: EmployeeRepository,
) {

    @BeforeEach
    fun setup() {
        employeeRepository.saveAll(
            listOf(
                EmployeeBuilder.buildEmployee(
                    id = "HUBE",
                    firstName = "Franz",
                    lastName = "Huber",
                ),
                EmployeeBuilder.buildEmployee(
                    id = "SCHM",
                    firstName = "Barbara",
                    lastName = "Schmidt",
                ),
                EmployeeBuilder.buildEmployee(
                    id = "BAUE",
                    firstName = "Fritz",
                    lastName = "Bauer",
                )
            )
        )
    }

    @Nested
    inner class GetEmployeeListTests {

        @Test
        fun `returns all employees`() {
            val employeeList = getEmployeeListPersistenceAdapter.getEmployeeList()

            assertThat(employeeList).isNotNull.hasSize(2)
            assertThat(employeeList.map { it.firstName }).containsExactly("Franz", "Barbara", "Fritz")
        }
    }

    @Nested
    @DisplayName("finds employees where")
    inner class GetFilteredEmployeeList {

        @Test
        fun `partial name matches in first name`() {
            val filteredEmployeeList = getEmployeeListPersistenceAdapter.getFilteredEmployeeList(partialName = "Fr")

            assertThat(filteredEmployeeList).isNotNull
            assertThat(filteredEmployeeList.map { it.id }).containsExactly("HUBE", "BAUE")
        }

        @Test
        fun `partial name matches in last name`() {
            val filteredEmployeeList = getEmployeeListPersistenceAdapter.getFilteredEmployeeList(partialName = "Sch")

            assertThat(filteredEmployeeList).isNotNull
            assertThat(filteredEmployeeList.map { it.id }).containsExactly("SCHM")
        }

        @Test
        fun `partial name matches in last name or first name`() {
            val filteredEmployeeList = getEmployeeListPersistenceAdapter.getFilteredEmployeeList(partialName = "Ba")

            assertThat(filteredEmployeeList).isNotNull
            assertThat(filteredEmployeeList.map { it.id }).containsExactly("SCHM", "BAUE")
        }

        @Test
        fun `partial name matches case insensitive`() {
            val filteredEmployeeList = getEmployeeListPersistenceAdapter.getFilteredEmployeeList(partialName = "ba")

            assertThat(filteredEmployeeList).isNotNull
            assertThat(filteredEmployeeList.map { it.id }).containsExactly("SCHM", "BAUE")
        }
    }

}
