package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.model.Usuario
import org.springframework.stereotype.Service

@Service
class AutorService (private var autores: List<Usuario> = ArrayList()) {

    init {
        autores = listOf(
            Usuario(
                id = 1,
                nome = "José",
                email = "jose.l.c@"
            ),
            Usuario(
                id = 2,
                nome = "maria",
                email = "maria.l.c@"
            )
        )
    }

    fun buscarPorId(id: Long): Usuario = autores.stream().filter { it -> it.id == id }.findFirst().get()
}
