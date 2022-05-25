package com.kotlin.forumkotlin.repository

import com.kotlin.forumkotlin.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeDoCurso: String): List<Topico>
}