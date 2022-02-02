package org.rbleuse.betterreadsdataloader.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class BookDumpDto(
    @JsonProperty("key")
    val id: String = "",
    @JsonProperty("title")
    val title: String = "",
    @JsonProperty("description")
    val description: StringValueDto?,
    @JsonProperty("created")
    val created: DateValueDto,
    @JsonProperty("covers")
    val covers: List<String> = emptyList(),
    @JsonProperty("authors")
    val authors: List<AuthorsDto> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class StringValueDto(
    @JsonProperty("value")
    val value: String = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DateValueDto(
    @JsonProperty("value")
    val value: LocalDate
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AuthorsDto(
    @JsonProperty("author")
    val author: AuthorDto
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AuthorDto(
    @JsonProperty("key")
    val key: String
)