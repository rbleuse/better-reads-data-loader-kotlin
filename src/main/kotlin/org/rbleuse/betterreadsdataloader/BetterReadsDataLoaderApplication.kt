package org.rbleuse.betterreadsdataloader

import com.datastax.oss.driver.api.core.CqlSessionBuilder
import org.rbleuse.betterreadsdataloader.connection.DataStaxAstraProperties
import org.rbleuse.betterreadsdataloader.domain.Author
import org.rbleuse.betterreadsdataloader.repository.AuthorRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import java.nio.file.Path

@SpringBootApplication
@ConfigurationPropertiesScan
class BetterReadsDataLoaderApplication
constructor(@Lazy val authorRepository: AuthorRepository) {

    /**
     * This is necessary to have the Spring Boot app use the Astra secure bundle
     * to connect to the database
     */
    @Bean
    fun sessionBuilderCustomizer(astraProperties: DataStaxAstraProperties): CqlSessionBuilderCustomizer {
        val bundle: Path = astraProperties.secureConnectBundle.toPath()
        return CqlSessionBuilderCustomizer { builder: CqlSessionBuilder -> builder.withCloudSecureConnectBundle(bundle) }
    }

    @EventListener(ApplicationReadyEvent::class)
    fun start() {
        val author = Author("id", "Name", "personalName")
        authorRepository.save(author)
    }
}

fun main(args: Array<String>) {
    runApplication<BetterReadsDataLoaderApplication>(*args)
}
