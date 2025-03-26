package gogo.gogoshop.global.kafka.consumer.dto

data class StageConfirmEvent(
    val id: String,
    val stageId: Long
)
