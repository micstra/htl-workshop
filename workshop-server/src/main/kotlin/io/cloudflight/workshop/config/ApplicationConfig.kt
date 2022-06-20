package io.cloudflight.workshop.config

import io.cloudflight.workshop.adapter.out.persistence.config.PersistenceConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    PersistenceConfig::class,
)
class ApplicationConfig
