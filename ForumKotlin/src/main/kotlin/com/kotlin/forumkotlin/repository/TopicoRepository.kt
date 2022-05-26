package com.kotlin.forumkotlin.repository

import com.kotlin.forumkotlin.dto.TopicoPorCategoriaDto
import com.kotlin.forumkotlin.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeDoCurso: String, pageable: Pageable): Page<Topico>

    @Query("SELECT new com.kotlin.forumkotlin.dto.TopicoPorCategoriaDto(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso " +
            "GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoriaDto>
}