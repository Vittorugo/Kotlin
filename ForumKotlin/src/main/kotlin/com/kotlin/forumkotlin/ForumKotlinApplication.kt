package com.kotlin.forumkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class ForumKotlinApplication

fun main(args: Array<String>) {

    run {
        println("Simulando uma API de Fórum para aperfeiçoar meus estudos em Kotlin")
    }

    runApplication<ForumKotlinApplication>(*args)
}
