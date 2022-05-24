package com.kotlin.forumkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ForumKotlinApplication

fun main(args: Array<String>) {

    run {
        println("Simulando uma API de Fórum para aperfeiçoar meus estudos em Kotlin")
    }
    runApplication<ForumKotlinApplication>(*args)
}
