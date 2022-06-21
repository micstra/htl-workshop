package io.cloudflight.workshop.adapter.out.persistence.employee

import io.cloudflight.workshop.application.usecase.EmployeeUpdateInfo
import io.cloudflight.workshop.application.usecase.SaveOrUpdateEmployeePort
import org.springframework.stereotype.Service

@Service
class SaveOrUpdateEmployeeAdapter(
        private val employeeRepository: EmployeeRepository
) : SaveOrUpdateEmployeePort {

    override fun saveOrUpdateEmployee(employee: EmployeeUpdateInfo) {
        employeeRepository.save(employee.toEntity())
    }

    private fun EmployeeUpdateInfo.toEntity(): Employee {
        return Employee(id, firstName, lastName)
    }
}