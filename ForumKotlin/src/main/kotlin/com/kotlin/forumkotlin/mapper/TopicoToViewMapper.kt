package com.kotlin.forumkotlin.mapper

import com.kotlin.forumkotlin.dto.TopicoView
import com.kotlin.forumkotlin.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoToViewMapper: Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
        return TopicoView (
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao,
            status = t.status
        )
    }
}