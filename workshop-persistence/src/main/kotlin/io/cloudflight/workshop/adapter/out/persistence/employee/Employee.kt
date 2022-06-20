package io.cloudflight.workshop.adapter.out.persistence.employee

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "staff")
class Employee(

    @Column
    @Id
    val id: String = "",

    @Column(nullable = false, length = 255)
    val firstName: String = "",

    @Column(nullable = false, length = 255)
    val lastName: String = "",
)
