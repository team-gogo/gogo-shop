package gogo.gogoshop.global.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import gogo.gogoshop.global.kafka.consumer.dto.TicketAdditionFailedEvent
import gogo.gogoshop.global.kafka.properties.KafkaTopics.TICKET_ADDITION_FAILED
import gogo.gogoshop.global.saga.TicketAdditionFailedSaga
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.AcknowledgingMessageListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

@Service
class TicketAdditionFailedConsumer(
    private val objectMapper: ObjectMapper,
    private val ticketAdditionFailedSaga: TicketAdditionFailedSaga
): AcknowledgingMessageListener<String, String> {

    private val log = LoggerFactory.getLogger(this::class.java.simpleName)

    @KafkaListener(
        topics = [TICKET_ADDITION_FAILED],
        groupId = "gogo",
        containerFactory = "ticketAdditionFailedEventListenerContainerFactory"
    )
    override fun onMessage(data: ConsumerRecord<String, String>, acknowledgment: Acknowledgment?) {
        val (key, event) = data.key() to objectMapper.readValue(data.value(), TicketAdditionFailedEvent::class.java)
        log.info("${TICKET_ADDITION_FAILED}_topic, key: $key, event: $event")

        ticketAdditionFailedSaga.rollbackBoughtShopTicket(event.miniGameId, event.ticketType, event.shopReceiptId)

        acknowledgment!!.acknowledge()
    }
}