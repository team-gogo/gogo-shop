package gogo.gogoshop.global.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import gogo.gogoshop.global.kafka.consumer.dto.UserTicketQuantityAddFailedEvent
import gogo.gogoshop.global.kafka.properties.KafkaTopics.USER_TICKET_QUANTITY_ADD_FAILED
import gogo.gogoshop.global.saga.UserTicketQuantityAddFailedSaga
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.AcknowledgingMessageListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

@Service
class UserTicketQuantityAddFailedConsumer(
    private val objectMapper: ObjectMapper,
    private val userTicketQuantityAddFailedSaga: UserTicketQuantityAddFailedSaga
): AcknowledgingMessageListener<String, String> {

    private val log = LoggerFactory.getLogger(this::class.java.simpleName)

    @KafkaListener(
        topics = [USER_TICKET_QUANTITY_ADD_FAILED],
        groupId = "gogo",
        containerFactory = "userTicketQuantityAddFailedEventListenerContainerFactory"
    )
    override fun onMessage(data: ConsumerRecord<String, String>, acknowledgment: Acknowledgment?) {
        val (key, event) = data.key() to objectMapper.readValue(data.value(), UserTicketQuantityAddFailedEvent::class.java)
        log.info("${USER_TICKET_QUANTITY_ADD_FAILED}_topic, key: $key, event: $event")

        userTicketQuantityAddFailedSaga.rollbackBoughtShopTicket(event.miniGameId, event.ticketType, event.shopReceiptId)

        acknowledgment!!.acknowledge()
    }
}