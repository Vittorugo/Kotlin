package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.model.Curso
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@Service
class CursoService(private var cursos: List<Curso> = ArrayList()) {

    init {
        cursos = listOf(
            Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
            Curso(id = 2, nome = "Java", categoria = "Programação"),
            Curso(id = 3, nome = "Spring", categoria = "Programação"),
        )
    }

    fun listar(): List<Curso> = this.cursos

    fun buscarPorId(id: Long): Curso = this.cursos.stream().filter{ it -> it.id == id }.findFirst().get()

    fun cadastrar(curso: Curso): Curso {
        curso.id = curso.id ?: (cursos.size + 1).toLong()
        cursos = this.cursos.plus(curso)

        return curso
    }

    fun atualizar(id: Long, curso: Curso): Curso? {
        val cursoDaBase: Curso = this.buscarPorId(id)
        this.cursos.minus(cursoDaBase)
        val novoCurso: Curso = Curso(
            id = curso.id ?: (cursos.size + 1).toLong(),
            nome = curso.nome,
            categoria = curso.categoria
        )
        cursos = this.cursos.plus(novoCurso)
        return novoCurso
    }

}
