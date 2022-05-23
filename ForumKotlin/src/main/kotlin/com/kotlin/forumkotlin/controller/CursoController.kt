package com.kotlin.forumkotlin.controller

import com.kotlin.forumkotlin.dto.CursoForm
import com.kotlin.forumkotlin.dto.CursoView
import com.kotlin.forumkotlin.model.Curso
import com.kotlin.forumkotlin.service.CursoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/curso")
class CursoController(
    private val serviceCurso: CursoService
) {

    @GetMapping("listar")
    fun listar(): ResponseEntity<List<CursoView>> = ResponseEntity.ok(serviceCurso.listar())

    @PostMapping("cadastrar")
    fun cadastrar(@RequestBody @Valid curso: CursoForm, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<CursoView> {
        val novoCurso: CursoView = serviceCurso.cadastrar(curso)
        val uri: URI = uriComponentsBuilder.path("/curso/${novoCurso.id}").build().toUri()
        return ResponseEntity.created(uri).body(novoCurso)
    }

    @PutMapping("atualizar/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody curso: CursoForm): ResponseEntity<CursoView> =
        ResponseEntity.ok(serviceCurso.atualizar(id, curso))

    @DeleteMapping("deletar/{id}")
    fun deletar(@PathVariable id: Long): ResponseEntity<String> = ResponseEntity.ok(serviceCurso.deletar(id))
}