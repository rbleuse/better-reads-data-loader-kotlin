package org.rbleuse.betterreadsdataloaderkotlin.repository

import org.rbleuse.betterreadsdataloaderkotlin.domain.Book
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CassandraRepository<Book, String>
