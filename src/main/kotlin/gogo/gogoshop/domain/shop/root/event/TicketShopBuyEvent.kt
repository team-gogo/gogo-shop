package gogo.gogoshop.domain.shop.root.event

import gogo.gogoshop.domain.shop.receipt.persistence.TicketType

data class TicketShopBuyEvent(
    val id: String,
    val studentId: Long,
    val shopId: Long,
    val miniGameId: Long,
    val ticketType: TicketType,
    val purchaseQuantity: Int,
)
