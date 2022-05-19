package com.kotlin.forumkotlin.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/*
    Obs: Como se trata de uma data class o BeanValidation solicita que antes das notações de validação deve-se adicionar
    o termo 'field:' como demonstrado abaixo. Isso fará com que o Spring entenda que as validações estão sendo feitas nos
    atributos, ou, se fosse o caso, nos getters e setters, ou ainda, outros métodos e não nos parâmetros do construtor.
*/

data class TopicoForm (
    @field:NotEmpty(message = "Titulo não pode ser vazio!")
    @field:Size(min=5, max = 100,message = "Titulo deve ter entre 5 e 100 caracteres!")
    val titulo: String,
    @field:NotEmpty val mensagem: String,
    @field:NotNull  val idCurso: Long,
    @field:NotNull  val idAutor: Long
    )
