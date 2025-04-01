package gogo.gogoshop.domain.shop.root.event

import gogo.gogoshop.domain.shop.receipt.persistence.TicketType

data class TicketShopBuyEvent(
    val id: String,
    val studentId: Long,
    val shopId: Long,
    val stageId: Long,
    val shopMiniGameId: Long,
    val ticketType: TicketType,
    val shopReceiptId: Long,
    val ticketPrice: Long,
    val purchaseQuantity: Int,
)
