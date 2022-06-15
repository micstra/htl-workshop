package io.cloudflight.workshop

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackageClasses = [WorkshopApplication::class])
class WorkshopApplication

fun main(args: Array<String>) {
    SpringApplication.run(WorkshopApplication::class.java, *args)
}
