package br.com.alura.ecommerce.produce

import br.com.alura.ecommerce.util.GsonSerializer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class KafkaDispatcher<T> {

    private var producer: KafkaProducer<String, T> = KafkaProducer<String, T>(properties())

    fun send(topic : String, key: String, value: T) {
        val record = ProducerRecord<String, T>(topic, key, value);
        val callback = fun (data: RecordMetadata?, exception: Exception?) {
            exception?.printStackTrace()
            println("Sucesso enviando ${data?.topic()} :::partição: ${data?.partition()} / offset: ${data?.offset()} / timestamp: ${data?.timestamp()}")
        }
        producer.send(record,callback).get()
    }

    private fun properties() : Properties{
        val properties = Properties()
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer::class.java.name)
        return properties
    }
}