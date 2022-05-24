package com.kotlin.forumkotlin.repository

import com.kotlin.forumkotlin.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}