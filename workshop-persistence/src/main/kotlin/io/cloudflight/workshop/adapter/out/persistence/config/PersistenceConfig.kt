package io.cloudflight.workshop.adapter.out.persistence.config

import io.cloudflight.workshop.repositories.EmployeeRepository
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackageClasses = [EmployeeRepository::class])
class PersistenceConfig
