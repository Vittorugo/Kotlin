import org.apache.kafka.clients.producer.*
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

fun main(args: Array<String>) {
    println("Hello World, Kafka!")

    val producer: KafkaProducer<String,String> = KafkaProducer<String, String>(properties())
    val value = "123,456,789"
    val record: ProducerRecord<String, String> = ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", value, value)
    producer.send(record) { data: RecordMetadata?, exception: Exception? ->
        exception?.printStackTrace()
        println("Sucesso enviando ${data?.topic()} :::partição: ${data?.partition()} / offset: ${data?.offset()} / timestamp: ${data?.timestamp()}")
    }.get()
}

fun properties() : Properties{
    val properties = Properties()
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
    return properties
}