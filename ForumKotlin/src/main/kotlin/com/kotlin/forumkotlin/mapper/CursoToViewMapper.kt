package com.kotlin.forumkotlin.mapper

import com.kotlin.forumkotlin.dto.CursoView
import com.kotlin.forumkotlin.model.Curso
import org.springframework.stereotype.Component

@Component
class CursoToViewMapper : Mapper<Curso, CursoView> {
    override fun map(c: Curso): CursoView {
        return CursoView(
            id = c.id,
            nome = c.nome,
            categoria = c.categoria
        )
    }
}