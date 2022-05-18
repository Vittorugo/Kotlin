package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private var cursos: List<Curso> = ArrayList()) {

    init {
        cursos = listOf(
            Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
            Curso(id = 2, nome = "Java", categoria = "Programação"),
            Curso(id = 3, nome = "Spring", categoria = "Programação"),
        )
    }

    fun buscarPorId(id: Long): Curso = this.cursos.stream().filter{ it -> it.id == id }.findFirst().get()
}
