package org.rbleuse.betterreadsdataloaderkotlin.connection

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.io.File

@ConstructorBinding
@ConfigurationProperties("datastax.astra")
data class DataStaxAstraProperties(
    val secureConnectBundle: File
)

@ConstructorBinding
@ConfigurationProperties("datadump.location")
data class DatadumpLocationProperties(
    val authors: String,
    val works: String,
)
