package com.kotlin.forumkotlin.controller

import com.kotlin.forumkotlin.model.*
import com.kotlin.forumkotlin.service.TopicoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/forum")
class TopicoController ( private val service: TopicoService) {

    @GetMapping("listarTopicos")
    fun listar(): ResponseEntity<List<Topico>> = ResponseEntity.ok(service.listar())

    @GetMapping("listarTopico/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Topico> = ResponseEntity.ok(service.listarPorId(id))
}