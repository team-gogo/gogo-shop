package gogo.gogoshop.global.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import gogo.gogoshop.domain.shop.root.application.ShopProcessor
import gogo.gogoshop.global.kafka.consumer.dto.StageConfirmEvent
import gogo.gogoshop.global.kafka.properties.KafkaTopics.STAGE_CONFIRM
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.AcknowledgingMessageListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

@Service
class StageConfirmConsumer(
    private val objectMapper: ObjectMapper,
    private val shopProcessor: ShopProcessor
): AcknowledgingMessageListener<String, String> {

    private val log = LoggerFactory.getLogger(this::class.java.simpleName)

    @KafkaListener(
        topics = [STAGE_CONFIRM],
        groupId = "gogo",
        containerFactory = "stageConfirmEventListenerContainerFactory"
    )
    override fun onMessage(data: ConsumerRecord<String, String>, acknowledgment: Acknowledgment?) {
        val (key, event) = data.key() to objectMapper.readValue(data.value(), StageConfirmEvent::class.java)
        log.info("${STAGE_CONFIRM}_topic, key: $key, event: $event")

        shopProcessor.shopActiveToAble(event.stageId)

        acknowledgment!!.acknowledge()
    }
}