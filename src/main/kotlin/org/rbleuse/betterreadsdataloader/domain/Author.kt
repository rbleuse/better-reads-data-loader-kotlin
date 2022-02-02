package org.rbleuse.betterreadsdataloader.domain

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.CassandraType.Name
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

@Table("author_by_id")
data class Author(

    @Id
    @PrimaryKeyColumn(name = "author_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    val id: String,

    @get:Column("author_name")
    @CassandraType(type = Name.TEXT)
    val name: String,

    @get:Column("personal_name")
    @CassandraType(type = Name.TEXT)
    val personalName: String
)