package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.dto.AtualizacaoTopicoForm
import com.kotlin.forumkotlin.dto.TopicoForm
import com.kotlin.forumkotlin.dto.TopicoView
import com.kotlin.forumkotlin.exception.NotFoundException
import com.kotlin.forumkotlin.mapper.FormToTopicoMapper
import com.kotlin.forumkotlin.mapper.TopicoToViewMapper
import com.kotlin.forumkotlin.model.Curso
import com.kotlin.forumkotlin.model.Topico
import com.kotlin.forumkotlin.model.Usuario
import com.kotlin.forumkotlin.repository.TopicoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService (
    private val repository: TopicoRepository,
    private val topicoToViewMapper: TopicoToViewMapper,
    private val topicoFormMapper: FormToTopicoMapper,
    private val notFoundMessage: String = "Topico não encontrado!"
) {

    fun listar(): List<TopicoView> = repository.findAll().stream().map { it ->
        topicoToViewMapper.map(it) }
        .collect(Collectors.toList())

    fun listarPorId(id: Long): TopicoView {
        val topico: Topico = repository.findById(id).stream().filter { it -> it.id == id }.findFirst()
            .orElseThrow {NotFoundException(notFoundMessage)}
        return topicoToViewMapper.map(topico)
    }

    fun cadastrar(dto: TopicoForm): TopicoView {
        val novoTopico = topicoFormMapper.map(dto)
        repository.save(novoTopico)
        return topicoToViewMapper.map(novoTopico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView? {
        val topico: Topico = repository.findById(form.id).orElseThrow {NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem

        return topicoToViewMapper.map(topico)
    }

    fun deletar(id: Long): String {
        repository.deleteById(id)
        return "Tópico removido com sucesso."
    }
}