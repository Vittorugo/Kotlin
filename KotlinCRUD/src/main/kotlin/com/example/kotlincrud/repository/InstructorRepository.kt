package com.example.kotlincrud.repository

import com.example.kotlincrud.model.Instructor
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RestResource

interface InstructorRepository: CrudRepository<Instructor, Long> {

    @RestResource(exported = false)
    override fun delete(instructor: Instructor)

    @RestResource(exported = false)
    override fun deleteById(id: Long)

    @RestResource(exported = false)
    override fun deleteAll()
}