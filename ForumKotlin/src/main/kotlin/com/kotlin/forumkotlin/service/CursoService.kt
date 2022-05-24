package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.dto.CursoForm
import com.kotlin.forumkotlin.dto.CursoView
import com.kotlin.forumkotlin.exception.NotFoundException
import com.kotlin.forumkotlin.mapper.CursoToViewMapper
import com.kotlin.forumkotlin.mapper.FormToCursoMapper
import com.kotlin.forumkotlin.model.Curso
import com.kotlin.forumkotlin.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CursoService(
    private val repository: CursoRepository,
    private val cursoToViewMapper: CursoToViewMapper,
    private val formToCursoMapper: FormToCursoMapper,
    private val notFoundMessage: String = "Curso não encontrado!"
) {

    fun listar(): List<CursoView> = repository.findAll().stream().map { it -> cursoToViewMapper.map(it) }.collect(Collectors.toList())

    fun buscarPorId(id: Long): Curso {
        return repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun cadastrar(cursoForm: CursoForm): CursoView {
        val novoCurso: Curso = formToCursoMapper.map(cursoForm)
        repository.save(novoCurso)
        return cursoToViewMapper.map(novoCurso)
    }

    fun atualizar(id: Long, cursoForm: CursoForm): CursoView? {
        val curso: Curso = this.buscarPorId(id)
        curso.nome = cursoForm.nome
        curso.categoria = cursoForm.categoria
        return cursoToViewMapper.map(curso)
    }

    fun deletar(id: Long): String {
        repository.deleteById(id)
        return "Curso removido com sucesso!"
    }
}
