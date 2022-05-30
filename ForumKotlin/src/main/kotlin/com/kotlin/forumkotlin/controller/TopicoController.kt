package com.kotlin.forumkotlin.controller

import com.kotlin.forumkotlin.dto.AtualizacaoTopicoForm
import com.kotlin.forumkotlin.dto.TopicoForm
import com.kotlin.forumkotlin.dto.TopicoPorCategoriaDto
import com.kotlin.forumkotlin.dto.TopicoView
import com.kotlin.forumkotlin.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.DEFAULT_DIRECTION
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController ( private val service: TopicoService) {

    @GetMapping("listarTopicos")
    @Cacheable(value = ["topicos"])
    fun listar(@RequestParam(required = false) nomeDoCurso: String?,
               @PageableDefault(size = 2, sort = ["dataCriacao"], direction = Sort.Direction.DESC) pageable: Pageable): ResponseEntity<Page<TopicoView>> =
        ResponseEntity.ok(service.listar(nomeDoCurso, pageable))

    @GetMapping("listarTopico/{id}")
    @Cacheable(value = ["topicoId"])
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<TopicoView> = ResponseEntity.ok(service.listarPorId(id))

    @PostMapping("cadastrar")
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(@RequestBody @Valid dto: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        val topicoView: TopicoView = service.cadastrar(dto)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping("atualizar")
    @Transactional
    @CacheEvict(value = ["topicos", "topicoId"], allEntries = true)
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> =
        ResponseEntity.ok(service.atualizar(form))

    @DeleteMapping("deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topicos, topicoId"], allEntries = true)
    fun deletar(@PathVariable id: Long): ResponseEntity<String> = ResponseEntity.ok(service.deletar(id))

    @GetMapping("relatorio")
    fun relatorio(@PageableDefault(size = 5, sort = ["categoria"]) pageable: Pageable): ResponseEntity<Page<TopicoPorCategoriaDto>> =
        ResponseEntity.ok(service.relatorio(pageable))
}