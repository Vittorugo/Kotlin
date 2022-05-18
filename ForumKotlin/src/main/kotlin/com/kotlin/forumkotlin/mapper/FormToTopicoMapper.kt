package com.kotlin.forumkotlin.mapper

import com.kotlin.forumkotlin.dto.TopicoForm
import com.kotlin.forumkotlin.model.Topico
import com.kotlin.forumkotlin.service.AutorService
import com.kotlin.forumkotlin.service.CursoService
import org.springframework.stereotype.Component

@Component
class FormToTopicoMapper(
    private val cursoService: CursoService,
    private val autorService: AutorService
): Mapper<TopicoForm, Topico> {

    override fun map(form: TopicoForm): Topico {
        return Topico (
            titulo = form.titulo,
            mensagem = form.mensagem,
            curso = cursoService.buscarPorId(form.idCurso),
            autor = autorService.buscarPorId(form.idAutor)
        )
    }

}
