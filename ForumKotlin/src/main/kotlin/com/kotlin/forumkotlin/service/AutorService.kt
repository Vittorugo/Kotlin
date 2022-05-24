package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.model.Usuario
import com.kotlin.forumkotlin.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class AutorService (private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario = repository.getById(id)
}
