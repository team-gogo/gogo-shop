package gogo.gogoshop.domain.shop.receipt.application

import gogo.gogoshop.domain.shop.receipt.application.dto.ReceiptResDto

interface ShopReceiptService {
    fun getShopReceipt(shopId: Long): ReceiptResDto
}