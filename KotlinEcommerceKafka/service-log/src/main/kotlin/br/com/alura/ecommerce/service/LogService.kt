package br.com.alura.ecommerce.service

import org.apache.kafka.clients.consumer.ConsumerRecord
import java.util.regex.Pattern

fun main(args: Array<String>) {
    try {
        val service = KafkaService(
            group = "LogService",
            topic = Pattern.compile("ECOMMERCE.*"),
            parse = ::parseLog
        )
        service.run()
    } finally {
        println("EmailService Finished!")
    }
}

fun parseLog(r: ConsumerRecord<String, String>) {
    println("________________________________________")
    println("LOG: ${r.topic()}")
    println(r.key())
    println(r.value())
    println(r.partition())
    println(r.offset())
}



