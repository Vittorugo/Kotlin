package com.example.kotlincrud.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Instructor (
    @Id
    @GeneratedValue
    var id: Long? = null,
    var name: String? = null,
    var surname: String? = null,
    var email: String? = null
)