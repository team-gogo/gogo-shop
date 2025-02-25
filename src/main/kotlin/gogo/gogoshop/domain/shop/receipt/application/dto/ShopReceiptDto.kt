package gogo.gogoshop.domain.shop.receipt.application.dto

import gogo.gogoshop.domain.shop.receipt.persistence.TicketType
import java.time.LocalDateTime

data class ReceiptResDto(
    val receipt: List<ShopTicketReceiptDto>
)

data class ShopTicketReceiptDto(
    val ticketPrice: Int,
    val ticketQuantity: Int,
    val ticketType: TicketType,
    val purchaseDate: LocalDateTime
)
