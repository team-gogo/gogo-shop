package gogo.gogoshop.global.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import gogo.gogoshop.global.kafka.consumer.dto.TicketPointMinusFailedEvent
import gogo.gogoshop.global.kafka.properties.KafkaTopics.TICKET_POINT_MINUS_FAILED
import gogo.gogoshop.global.saga.TicketPointMinusFailedSaga
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.AcknowledgingMessageListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

@Service
class TicketPointMinusFailedConsumer(
    private val objectMapper: ObjectMapper,
    private val ticketPointMinusFailedSaga: TicketPointMinusFailedSaga
): AcknowledgingMessageListener<String, String> {

    private val log = LoggerFactory.getLogger(this::class.java.simpleName)

    @KafkaListener(
        topics = [TICKET_POINT_MINUS_FAILED],
        groupId = "gogo",
        containerFactory = "ticketPointMinusFailedEventListenerContainerFactory"
    )
    override fun onMessage(data: ConsumerRecord<String, String>, acknowledgment: Acknowledgment?) {
        val (key, event) = data.key() to objectMapper.readValue(data.value(), TicketPointMinusFailedEvent::class.java)
        log.info("${TICKET_POINT_MINUS_FAILED}_topic, key: $key, event: $event")

        ticketPointMinusFailedSaga.rollbackBoughtTicket(event.shopMiniGameId, event.ticketType, event.shopReceiptId)

        acknowledgment!!.acknowledge()
    }

}