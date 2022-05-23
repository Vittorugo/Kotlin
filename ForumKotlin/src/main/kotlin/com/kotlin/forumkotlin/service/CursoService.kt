package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.dto.CursoForm
import com.kotlin.forumkotlin.dto.CursoView
import com.kotlin.forumkotlin.mapper.CursoToViewMapper
import com.kotlin.forumkotlin.mapper.FormToCursoMapper
import com.kotlin.forumkotlin.model.Curso
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CursoService(
    private var cursos: List<Curso> = ArrayList(),
    private val cursoToViewMapper: CursoToViewMapper,
    private val formToCursoMapper: FormToCursoMapper
) {

    init {
        cursos = listOf(
            Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
            Curso(id = 2, nome = "Java", categoria = "Programação"),
            Curso(id = 3, nome = "Spring", categoria = "Programação"),
        )
    }

    fun listar(): List<CursoView> = this.cursos.stream().map { it -> cursoToViewMapper.map(it) }.collect(Collectors.toList())

    fun buscarPorId(id: Long): Curso = this.cursos.stream().filter{ it -> it.id == id }.findFirst().get()

    fun cadastrar(cursoForm: CursoForm): CursoView {
        cursoForm.id = cursoForm.id ?: (cursos.size + 1).toLong()
        val novoCurso: Curso = formToCursoMapper.map(cursoForm)
        cursos = this.cursos.plus(novoCurso)

        val cursoView: CursoView = cursoToViewMapper.map(novoCurso)

        return cursoView
    }

    fun atualizar(id: Long, cursoForm: CursoForm): CursoView? {
        val cursoDaBase: Curso = this.buscarPorId(id)
        cursos = this.cursos.minus(cursoDaBase)

        val novoCurso: Curso = Curso(
            id = cursoForm.id ?: (cursos.size + 1).toLong(),
            nome = cursoForm.nome,
            categoria = cursoForm.categoria
        )
        cursos = this.cursos.plus(novoCurso)
        return cursoToViewMapper.map(novoCurso)
    }

    fun deletar(id: Long): String {
        val curso: Curso = this.buscarPorId(id)
        cursos = cursos.minus(curso)
        return "Curso removido com sucesso!"
    }


}
