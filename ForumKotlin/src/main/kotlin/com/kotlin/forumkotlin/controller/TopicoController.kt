package com.kotlin.forumkotlin.controller

import com.kotlin.forumkotlin.dto.TopicoForm
import com.kotlin.forumkotlin.dto.TopicoView
import com.kotlin.forumkotlin.service.TopicoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/forum")
class TopicoController ( private val service: TopicoService) {

    @GetMapping("listarTopicos")
    fun listar(): ResponseEntity<List<TopicoView>> = ResponseEntity.ok(service.listar())

    @GetMapping("listarTopico/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<TopicoView> = ResponseEntity.ok(service.listarPorId(id))

    @PostMapping("cadastrar")
    fun cadastrar(@RequestBody dto: TopicoForm): ResponseEntity<TopicoView> = ResponseEntity.ok(service.cadastrar(dto))
}