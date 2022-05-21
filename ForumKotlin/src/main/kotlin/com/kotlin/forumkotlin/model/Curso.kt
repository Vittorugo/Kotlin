package com.kotlin.forumkotlin.model

import javax.validation.constraints.NotEmpty

data class Curso (
    var id: Long? = null,
    @field:NotEmpty
    val nome: String,
    @field:NotEmpty
    val categoria: String
    )
