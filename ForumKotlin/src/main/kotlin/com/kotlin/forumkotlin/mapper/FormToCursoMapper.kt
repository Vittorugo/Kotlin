package com.kotlin.forumkotlin.mapper

import com.kotlin.forumkotlin.dto.CursoForm
import com.kotlin.forumkotlin.model.Curso
import org.springframework.stereotype.Component

@Component
class FormToCursoMapper: Mapper<CursoForm, Curso> {
    override fun map(c: CursoForm): Curso {
        return Curso(
            id = c.id,
            nome = c.nome,
            categoria = c.categoria
        )
    }
}