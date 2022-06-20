package io.cloudflight.workshop.config

import io.cloudflight.workshop.adapter.out.persistence.PersistenceModuleConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    PersistenceModuleConfig::class,
)
class ApplicationConfig
