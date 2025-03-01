package gogo.gogoshop.global.kafka.consumer.dto

import gogo.gogoshop.domain.shop.receipt.persistence.TicketType

data class ShopTicketBuyFailedEvent(
    val id: String,
    val shopId: Long,
    val ticketType: TicketType,
    val purchaseQuantity: Int
)
