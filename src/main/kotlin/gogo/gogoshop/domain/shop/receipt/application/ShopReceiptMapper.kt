package gogo.gogoshop.domain.shop.receipt.application

import gogo.gogoshop.domain.shop.receipt.application.dto.ShopTicketReceiptDto
import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceipt
import org.springframework.stereotype.Component

@Component
class ShopReceiptMapper {

    fun map(shopReceipt: List<ShopReceipt>) =
        shopReceipt.map { receipt ->
            ShopTicketReceiptDto(
                ticketPrice = receipt.ticketPrice,
                ticketQuantity = receipt.ticketQauntity,
                ticketType = receipt.ticketType,
                purchaseDate = receipt.purchaseDate
            )
        }



}