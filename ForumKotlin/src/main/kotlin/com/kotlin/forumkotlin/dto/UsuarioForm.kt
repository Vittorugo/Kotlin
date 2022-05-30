package com.kotlin.forumkotlin.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UsuarioForm(
   @field:NotNull
   @field:NotEmpty
   val nome: String,
   @field:NotNull
   @field:NotEmpty
   val email: String,
   @field:NotNull
   @field:NotEmpty
   val password: String
)