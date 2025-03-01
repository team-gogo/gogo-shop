package gogo.gogoshop.global.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import gogo.gogoshop.global.kafka.consumer.dto.ShopTicketBuyFailedEvent
import gogo.gogoshop.global.kafka.properties.KafkaTopics.SHOP_TICKET_BUY_FAILED
import gogo.gogoshop.global.saga.ShopTicketBuyFailedSaga
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.listener.AcknowledgingMessageListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

@Service
class ShopTicketBuyFailedConsumer(
    private val objectMapper: ObjectMapper,
    private val shopTicketBuyFailedSaga: ShopTicketBuyFailedSaga
): AcknowledgingMessageListener<String, String> {

    private val log = LoggerFactory.getLogger(this::class.java.simpleName)


    override fun onMessage(data: ConsumerRecord<String, String>, acknowledgment: Acknowledgment?) {
        val (key, event) = data.key() to objectMapper.readValue(data.value(), ShopTicketBuyFailedEvent::class.java)
        log.info("${SHOP_TICKET_BUY_FAILED}_topic, key: $key, event: $event")

        shopTicketBuyFailedSaga.cancelledTicketBuy(event.shopId, event.ticketType, event.purchaseQuantity)

        acknowledgment!!.acknowledge()
    }

}