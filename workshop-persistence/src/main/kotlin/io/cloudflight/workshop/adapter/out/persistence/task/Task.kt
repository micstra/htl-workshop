package io.cloudflight.workshop.adapter.out.persistence.task

import io.cloudflight.workshop.adapter.out.persistence.employee.Employee
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tasks")
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0

    @Column
    val description: String = ""

    @Column(name = "finished_date")
    val finishedAt: LocalDate? = null

    @Column
    val hoursWorked: Int = 0

    @ManyToOne
    @JoinColumn
    val employee: Employee? = null
}