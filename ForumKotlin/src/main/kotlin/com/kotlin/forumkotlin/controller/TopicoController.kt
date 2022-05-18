package com.kotlin.forumkotlin.controller

import com.kotlin.forumkotlin.dto.AtualizacaoTopicoForm
import com.kotlin.forumkotlin.dto.TopicoForm
import com.kotlin.forumkotlin.dto.TopicoView
import com.kotlin.forumkotlin.service.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/forum/topicos")
class TopicoController ( private val service: TopicoService) {

    @GetMapping("listarTopicos")
    fun listar(): ResponseEntity<List<TopicoView>> = ResponseEntity.ok(service.listar())

    @GetMapping("listarTopico/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<TopicoView> = ResponseEntity.ok(service.listarPorId(id))

    @PostMapping("cadastrar")
    fun cadastrar(@RequestBody @Valid dto: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        val topicoView: TopicoView = service.cadastrar(dto)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping("atualizar")
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> = ResponseEntity.ok(service.atualizar(form))

    @DeleteMapping("deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long): ResponseEntity<String> = ResponseEntity.ok(service.deletar(id))
}