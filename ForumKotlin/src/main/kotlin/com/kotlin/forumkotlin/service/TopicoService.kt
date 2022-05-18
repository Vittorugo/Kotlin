package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.dto.TopicoForm
import com.kotlin.forumkotlin.dto.TopicoView
import com.kotlin.forumkotlin.mapper.FormToTopicoMapper
import com.kotlin.forumkotlin.mapper.TopicoToViewMapper
import com.kotlin.forumkotlin.model.Curso
import com.kotlin.forumkotlin.model.Topico
import com.kotlin.forumkotlin.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService (
    private var topicos: List<Topico> = ArrayList(),
    private val topicoToViewMapper: TopicoToViewMapper,
    private val topicoFormMapper: FormToTopicoMapper
) {

    init {
        val topico: Topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis no Kotlin",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1,
                nome = "Victtor",
                email = "vhugoloureiro@"
            )
        )

        val topico2: Topico = Topico(
            id = 2,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis no Kotlin",
            curso = Curso(
                id = 2,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 2,
                nome = "Hugo",
                email = "vhugoloureiro@"
            )
        )

        this.topicos = Arrays.asList(topico,topico2)
    }

    fun listar(): List<TopicoView> = this.topicos.stream().map { it ->
        topicoToViewMapper.map(it) }
        .collect(Collectors.toList())

    fun listarPorId(id: Long): TopicoView {
        val topico: Topico = this.topicos.stream().filter { it -> it.id == id }.findFirst().get()
        return topicoToViewMapper.map(topico)
    }

    fun cadastrar(dto: TopicoForm): TopicoView {
        val novoTopico = topicoFormMapper.map(dto)
        novoTopico.id = (topicos.size + 1).toLong()
        topicos = this.topicos.plus(novoTopico)

        val topico: Topico = topicos.stream().filter { it -> it.titulo == dto.titulo && it.curso.id == dto.idCurso}.findFirst().get()
        return topicoToViewMapper.map(topico)
    }
}