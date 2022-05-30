package com.kotlin.forumkotlin.mapper

import com.kotlin.forumkotlin.dto.UsuarioView
import com.kotlin.forumkotlin.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioToViewMapper: Mapper<Usuario, UsuarioView> {
    override fun map(u: Usuario): UsuarioView {
        return UsuarioView(
            id= u.id,
            nome = u.nome,
            email = u.email,
            password = u.password
        )
    }
}
