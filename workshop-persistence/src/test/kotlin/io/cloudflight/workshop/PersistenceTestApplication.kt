package io.cloudflight.workshop

import io.cloudflight.workshop.adapter.out.persistence.PersistenceModuleConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(PersistenceModuleConfig::class)
internal class PersistenceTestApplication
