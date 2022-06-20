package io.cloudflight.workshop.config

import io.cloudflight.workshop.PersistenceTestApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [PersistenceTestApplication::class])
@ActiveProfiles("test")
internal annotation class PersistenceIntegrationTest
