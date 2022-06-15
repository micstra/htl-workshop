package io.cloudflight.workshop.controller

import io.cloudflight.workshop.api.EmployeeApiDelegate
import io.cloudflight.workshop.api.model.ReadEmployeeDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EmployeeController : EmployeeApiDelegate {

    override fun findAllEmployees(): ResponseEntity<List<ReadEmployeeDto>> {
        return ResponseEntity.ok(mockList())
    }


    private fun mockList(): List<ReadEmployeeDto> {
        return listOf(
                ReadEmployeeDto().apply {
                    id = "foo"
                    firstName = "baz"
                    lastName = "bar"
                },
                ReadEmployeeDto().apply {
                    id = "foobar"
                    firstName = "bazFoo"
                    lastName = "foo"
                }
        )
    }
}