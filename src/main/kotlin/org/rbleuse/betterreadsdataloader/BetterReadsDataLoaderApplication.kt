package org.rbleuse.betterreadsdataloader

import com.datastax.oss.driver.api.core.CqlSessionBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.rbleuse.betterreadsdataloader.connection.DataStaxAstraProperties
import org.rbleuse.betterreadsdataloader.domain.Author
import org.rbleuse.betterreadsdataloader.domain.Book
import org.rbleuse.betterreadsdataloader.dto.AuthorDumpDto
import org.rbleuse.betterreadsdataloader.dto.BookDumpDto
import org.rbleuse.betterreadsdataloader.repository.AuthorRepository
import org.rbleuse.betterreadsdataloader.repository.BookRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@SpringBootApplication
@ConfigurationPropertiesScan
class BetterReadsDataLoaderApplication
constructor(val authorRepository: AuthorRepository,
            val bookRepository: BookRepository,
            @Value("\${datadump.location.authors}") val authorDumpLocation: String,
            @Value("\${datadump.location.works}") val worksDumpLocation: String,
            val objectMapper: ObjectMapper) {

    private fun initAuthors() {
        val path = Paths.get(authorDumpLocation)

        Files.lines(path).use {
            it.forEach { line ->
                try {
                    val jsonString = line.substring(line.indexOf("{"))
                    val author = objectMapper.readValue(jsonString, AuthorDumpDto::class.java)

                    println("Inserting author ${author.name}...")
                    authorRepository.insert(
                        Author(author.id.removePrefix("/authors/"), author.name, author.personalName)
                    )
                } catch (e: Exception) {
                    println("Exception $e")
                }
            }
        }
    }

    private fun initWorks() {
        val path = Paths.get(worksDumpLocation)

        Files.lines(path).use { lines ->
            lines.forEach { line ->
                try {
                    val jsonString = line.substring(line.indexOf("{"))

                    val book = objectMapper.readValue(jsonString, BookDumpDto::class.java)
                    val authorIds = book.authors.map {
                        it.author.key.removePrefix("/authors/")
                    }
                    val authorNames = authorIds.map {
                        authorRepository.findByIdOrNull(it)?.name ?: "Unknown Author"
                    }

                    println("Inserting book ${book.title}...")
                    bookRepository.insert(
                        Book(
                            book.id.removePrefix("/works/"),
                            book.title,
                            book.description?.value ?: "",
                            book.created.value,
                            book.covers,
                            authorIds,
                            authorNames
                        )
                    )
                } catch (e: Exception) {
                    println("Exception $e")
                }
            }
        }
    }

    @EventListener(ApplicationReadyEvent::class)
    fun start() {
        initAuthors()
        initWorks()
    }
}

fun main(args: Array<String>) {
    runApplication<BetterReadsDataLoaderApplication>(*args)
}