package com.kotlin.forumkotlin.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var nome: String,
    var email: String,
    var password: String,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario.role")
    val role: List<Role> = mutableListOf()
)

