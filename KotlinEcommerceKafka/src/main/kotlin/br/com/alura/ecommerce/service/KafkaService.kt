package br.com.alura.ecommerce.service

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.io.Closeable
import java.time.Duration
import java.util.*
import kotlin.reflect.KFunction1

class KafkaService(
    private val group: String,
    private val topic: String,
    private val parse: KFunction1<ConsumerRecord<String, String>, Unit>
) : Closeable {
    private val consumer : KafkaConsumer<String, String> = KafkaConsumer<String, String>(properties())

    fun run() {
        consumer.subscribe(Collections.singletonList(topic))
        while (true) {
            val records : ConsumerRecords<String, String> = consumer.poll(Duration.ofMillis(100))
            if(!records.isEmpty) {
                for ( r in records) {
                    parse(r)
                }
            }
        }
    }

    private fun properties() : Properties {
        val properties = Properties()
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group)
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, "${group}-${UUID.randomUUID().toString()}")
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1")
        return properties
    }

    override fun close() {
        consumer.close()
    }
}