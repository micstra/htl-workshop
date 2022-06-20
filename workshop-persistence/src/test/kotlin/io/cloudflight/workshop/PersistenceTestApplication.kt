package io.cloudflight.workshop

import io.cloudflight.workshop.adapter.out.persistence.config.PersistenceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(PersistenceConfig::class)
internal class PersistenceTestApplication
