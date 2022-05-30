package com.kotlin.forumkotlin.mapper

import com.kotlin.forumkotlin.dto.UsuarioForm
import com.kotlin.forumkotlin.model.Usuario
import org.springframework.stereotype.Component

@Component
class FormToUsuairoMapper: Mapper<UsuarioForm,Usuario> {
    override fun map(u: UsuarioForm): Usuario {
        return Usuario(
            nome = u.nome,
            email = u.email,
            password = u.password
        )
    }
}
