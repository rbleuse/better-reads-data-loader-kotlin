package org.rbleuse.betterreadsdataloader.repository

import org.rbleuse.betterreadsdataloader.domain.Author
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : CassandraRepository<Author, String>
