package gogo.gogoshop.global.kafka.publisher

import gogo.gogoshop.domain.shop.root.event.ShopTicketBuyEvent
import gogo.gogoshop.global.kafka.properties.KafkaTopics.SHOP_TICKET_BUY
import gogo.gogoshop.global.publusher.TransactionEventPublisher
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ShopPublisher(
    private val transactionEventPublisher: TransactionEventPublisher
) {

    fun publishShopTicketBuyEvent(
        event: ShopTicketBuyEvent
    ) {
        val key = UUID.randomUUID().toString()
        transactionEventPublisher.publishEvent(
            topic = SHOP_TICKET_BUY,
            key = key,
            event = event
        )
    }

}