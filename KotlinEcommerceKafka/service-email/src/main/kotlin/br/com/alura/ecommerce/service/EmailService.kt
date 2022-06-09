package br.com.alura.ecommerce.service

import org.apache.kafka.clients.consumer.ConsumerRecord

fun main(args: Array<String>) {
    try {
        val service = KafkaService(
            group = "",
            topic = "",
            parse = ::parse
        )
        service.run()
    } finally {
        println("EmailService Finished!")
    }
}

fun parse(record: ConsumerRecord<String, String>) {
    println("________________________________________")
    println("Sending email, please wait a moment")
    println(record.key())
    println(record.value())
    println(record.partition())
    println(record.offset())
    try {
        Thread.sleep(5000)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
    println("Email sent")
}