import org.apache.kafka.clients.producer.*
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

fun main(args: Array<String>) {
    println("Hello World, Kafka!")

    val producer : KafkaProducer<String,String> = KafkaProducer<String, String>(propertiesProducer())
    val key = UUID.randomUUID().toString()
    val value = "${key}555,555,555"
    val email = "Thanks for shopping here."
    val record : ProducerRecord<String, String> = ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", key, value)
    val emailRecord : ProducerRecord<String, String> = ProducerRecord<String, String>("ECOMMERCE_SEND_EMAIL", key,email)

    val callback = fun (data: RecordMetadata?, exception: Exception?) {
        exception?.printStackTrace()
        println("Sucesso enviando ${data?.topic()} :::partição: ${data?.partition()} / offset: ${data?.offset()} / timestamp: ${data?.timestamp()}")
    }

    producer.send(record, callback).get()
    producer.send(emailRecord, callback).get()
}

fun propertiesProducer() : Properties{
    val properties = Properties()
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
    return properties
}