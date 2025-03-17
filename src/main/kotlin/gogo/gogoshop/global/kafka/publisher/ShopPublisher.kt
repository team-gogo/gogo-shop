package gogo.gogoshop.global.kafka.publisher

import gogo.gogoshop.domain.shop.root.event.TicketShopBuyEvent
import gogo.gogoshop.global.kafka.properties.KafkaTopics.TICKET_SHOP_BUY
import gogo.gogoshop.global.publusher.TransactionEventPublisher
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ShopPublisher(
    private val transactionEventPublisher: TransactionEventPublisher
) {

    fun publishShopTicketBuyEvent(
        event: TicketShopBuyEvent
    ) {
        val key = UUID.randomUUID().toString()
        transactionEventPublisher.publishEvent(
            topic = TICKET_SHOP_BUY,
            key = key,
            event = event
        )
    }

}