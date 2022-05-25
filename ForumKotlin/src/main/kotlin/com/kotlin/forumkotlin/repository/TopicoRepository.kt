package com.kotlin.forumkotlin.repository

import com.kotlin.forumkotlin.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeDoCurso: String, pageable: Pageable): Page<Topico>
}