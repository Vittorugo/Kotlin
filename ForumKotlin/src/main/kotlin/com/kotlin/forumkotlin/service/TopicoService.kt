package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.model.Curso
import com.kotlin.forumkotlin.model.Topico
import com.kotlin.forumkotlin.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService (private var topicos: List<Topico>) {

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

    fun listar(): List<Topico> = this.topicos

    fun listarPorId(id: Long): Topico {
        return this.topicos.stream().filter { it -> it.id == id }.findFirst().get()
    }
}