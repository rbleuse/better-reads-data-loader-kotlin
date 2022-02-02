package org.rbleuse.betterreadsdataloader.domain

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.CassandraType.Name
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDate

@Table("book_by_id")
data class Book (

    @Id
    @PrimaryKeyColumn(name = "book_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    val id: String,

    @get:Column("book_name")
    @CassandraType(type = Name.TEXT)
    val name: String,

    @get:Column("book_description")
    @CassandraType(type = Name.TEXT)
    val description: String,

    @get:Column("published_date")
    @CassandraType(type = Name.DATE)
    val publishedDate: LocalDate,

    @get:Column("cover_ids")
    @CassandraType(type = Name.LIST, typeArguments = [Name.TEXT])
    val coverIds: List<String>,

    @get:Column("author_names")
    @CassandraType(type = Name.LIST, typeArguments = [Name.TEXT])
    val authorNames: List<String>,

    @get:Column("author_ids")
    @CassandraType(type = Name.LIST, typeArguments = [Name.TEXT])
    val authorIds: List<String>
)