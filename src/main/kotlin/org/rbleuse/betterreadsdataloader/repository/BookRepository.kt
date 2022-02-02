package org.rbleuse.betterreadsdataloader.repository

import org.rbleuse.betterreadsdataloader.domain.Book
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CassandraRepository<Book, String>