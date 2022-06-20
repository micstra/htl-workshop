package io.cloudflight.workshop.adapter.out.persistence.employee

object EmployeeBuilder {
    internal fun buildEmployee(
        id: String = "Hube",
        firstName: String = "Franz",
        lastName: String = "Huber",
    ) = Employee(
        id = id,
        firstName = firstName,
        lastName = lastName
    )

}
