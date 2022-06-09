package br.com.alura.ecommerce.service

import org.apache.kafka.clients.consumer.ConsumerRecord

interface ConsumerFunction {
    fun consume(record : ConsumerRecord<String, String>)
}