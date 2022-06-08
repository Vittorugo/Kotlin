import br.com.alura.ecommerce.service.KafkaService
import org.apache.kafka.clients.consumer.ConsumerRecord

fun main(args: Array<String>) {
    try {
        val service = KafkaService(group = "FraudDetectorService", topic = "ECOMMERCE_NEW_ORDER", parse = ::parse)
        service.run()
    } finally {
        println("FraudDetectorService Finished!")
    }
}

private fun parse(record: ConsumerRecord<String, String>) {
    println("________________________________________")
    println("Processing new order, checking for fraud")
    println(record.key())
    println(record.value())
    println(record.partition())
    println(record.offset())
    try {
        Thread.sleep(1000)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
    println("Order processed")
}



