package org.rbleuse.betterreadsdataloaderkotlin.repository

import org.rbleuse.betterreadsdataloaderkotlin.domain.Author
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : CassandraRepository<Author, String>
