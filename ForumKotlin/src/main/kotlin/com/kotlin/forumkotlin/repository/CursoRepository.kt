package com.kotlin.forumkotlin.repository

import com.kotlin.forumkotlin.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}