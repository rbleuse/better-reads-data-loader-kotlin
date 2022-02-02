package org.rbleuse.betterreadsdataloader.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AuthorDumpDto(
    @JsonProperty("key")
    val id: String = "",
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("personal_name")
    val personalName: String = ""
)