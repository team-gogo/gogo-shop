package gogo.gogoshop.global.kafka.consumer.dto

import gogo.gogoshop.domain.shop.receipt.persistence.TicketType

data class UserTicketQuantityAddFailedEvent(
    val id: String,
    val miniGameId: Long,
    val ticketType: TicketType,
    val shopReceiptId: Long,
)
