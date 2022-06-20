package io.cloudflight.workshop.adapter.out.persistence.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Int> {
    fun findAllByEmployeeId(employeeId: String): List<Task>
}