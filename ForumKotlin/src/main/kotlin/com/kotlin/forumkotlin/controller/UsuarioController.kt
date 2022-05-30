package com.kotlin.forumkotlin.controller

import com.kotlin.forumkotlin.dto.AtualizacaoUsuarioForm
import com.kotlin.forumkotlin.dto.UsuarioForm
import com.kotlin.forumkotlin.dto.UsuarioView
import com.kotlin.forumkotlin.model.Usuario
import com.kotlin.forumkotlin.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/usuario")
class UsuarioController(
    private val service: UserService
) {

    @GetMapping("/listar")
    fun listarUsuarios(pageable: Pageable): Page<UsuarioView> = service.listar(pageable)

    @PostMapping("/cadastrar")
    fun cadastrar(@RequestBody @Valid usuario: UsuarioForm, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<UsuarioView> {
        val novoUsuario: UsuarioView = service.cadastrar(usuario)
        val uri = uriComponentsBuilder.path("/usuario/${novoUsuario.id}").build().toUri()
        return ResponseEntity.created(uri).body(novoUsuario)
    }

    @PutMapping("/atualizar")
    fun atualizar(@RequestBody usuario: AtualizacaoUsuarioForm): ResponseEntity<UsuarioView> = ResponseEntity.ok(service.atualizar(usuario))

    @DeleteMapping("/deletar/{id}")
    fun deletear(@PathVariable id: Long): String = service.deletar(id)
}