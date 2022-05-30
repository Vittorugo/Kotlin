package com.kotlin.forumkotlin.dto

import javax.validation.constraints.NotNull

data class AtualizacaoUsuarioForm(
    @field:NotNull
    val id: Long,
    @field:NotNull
    val nome: String,
    @field:NotNull
    val email: String,
    @field:NotNull
    val password: String
)