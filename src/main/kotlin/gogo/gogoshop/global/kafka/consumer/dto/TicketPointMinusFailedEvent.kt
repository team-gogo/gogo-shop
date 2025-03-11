package gogo.gogoshop.global.kafka.consumer.dto

import gogo.gogoshop.domain.shop.receipt.persistence.TicketType

data class TicketPointMinusFailedEvent(
    val id: String,
    val stageId: Long,
    val shopMiniGameId: Long,
    val ticketType: TicketType,
    val shopReceiptId: Long,
)
