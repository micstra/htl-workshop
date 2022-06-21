package io.cloudflight.workshop.application.usecase

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SaveOrUpdateEmployeeUseCase(
        private val saveOrUpdateEmployeePort: SaveOrUpdateEmployeePort
) {

    fun saveOrUpdate(id: String, firstName: String, lastName: String) {
        when {
            id.isEmpty() -> throw InvalidEmployeeException("id must not be empty")
            firstName.isEmpty() -> throw InvalidEmployeeException("firstname must not be empty")
            lastName.isEmpty() -> throw InvalidEmployeeException("lastname must not be empty")
            id.length > 6 -> throw InvalidEmployeeException("id must not be longer than 6 characters")
        }

        saveOrUpdateEmployeePort.saveOrUpdateEmployee(
                EmployeeUpdateInfo(id, firstName, lastName)
        )
    }

}

class InvalidEmployeeException(msg: String) : ResponseStatusException(HttpStatus.BAD_REQUEST, msg)

interface SaveOrUpdateEmployeePort {
    fun saveOrUpdateEmployee(employee: EmployeeUpdateInfo)
}

data class EmployeeUpdateInfo(
        val id: String,
        val firstName: String,
        val lastName: String
)