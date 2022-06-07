package br.com.alura.ecommerce.service

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

class FraudDetector {

//    companion object {
//
//        @JvmStatic fun executeConsumer() {
//            val consumer : KafkaConsumer<String, String> = KafkaConsumer<String, String>(propertiesConsumer())
//            consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"))
//            while (true) {
//                val records = consumer.poll(Duration.ofMillis(100))
//                if(!records.isEmpty) {
//                    for ( r in records) {
//                        println("________________________________________")
//                        println("Processing new order, checking for fraud")
//                        println(r.key())
//                        println(r.value())
//                        println(r.partition())
//                        println(r.offset())
//                        try {
//                            Thread.sleep(5000)
//                        } catch (e: InterruptedException) {
//                            e.printStackTrace()
//                        }
//                    }
//                    println("Order processed")
//                }
//            }
//        }
//
//        private fun propertiesConsumer(): Properties {
//            val properties = Properties()
//            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
//            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
//            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
//            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "FraudDetectorService")
//            return properties
//        }
//    }
}