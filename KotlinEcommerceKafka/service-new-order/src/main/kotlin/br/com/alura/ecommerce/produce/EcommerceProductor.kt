import br.com.alura.ecommerce.entities.Order
import br.com.alura.ecommerce.produce.KafkaDispatcher
import java.util.*

fun main(args: Array<String>) {
    println("Hello World, Kafka!")
    try {
        val orderDispatcher: KafkaDispatcher<Order> =
            KafkaDispatcher<Order>()
        val emailDispatcher: KafkaDispatcher<String> =
            KafkaDispatcher<String>()

        for (i in 0 .. 10) {
            val userId = UUID.randomUUID().toString()
            val orderId = UUID.randomUUID().toString()
            val amount = (Math.random() * 5000 + 1).toBigDecimal()

            val email = "Thanks for shopping here."
            val order: Order = Order(userId = userId, orderId = orderId, amount = amount)
            orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);
            emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
        }
    } finally {
        println("Message sending finished!")
    }
}

