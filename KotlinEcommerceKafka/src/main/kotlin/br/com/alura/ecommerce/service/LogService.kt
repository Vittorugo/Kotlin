package br.com.alura.ecommerce.service

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val consumer: KafkaConsumer<String, String> = KafkaConsumer<String, String>(properties())
    consumer.subscribe(Pattern.compile("ECOMMERCE.*"))
    while ( true) {
        val records: ConsumerRecords<String, String> = consumer.poll(Duration.ofMillis(100))
        if(!records.isEmpty) {
            println("Encontrei ${records.count()} registros")
            for ( r in records) {
                println("________________________________________")
                println("LOG: ${r.topic()}")
                println(r.key())
                println(r.value())
                println(r.partition())
                println(r.offset())
            }
        }
    }
}

fun properties() : Properties {
    val properties = Properties()
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "LogService")
    return properties
}