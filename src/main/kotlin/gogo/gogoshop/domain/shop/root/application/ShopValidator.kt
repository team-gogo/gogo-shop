package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.shop.root.persistence.ActiveShopStatus
import gogo.gogoshop.domain.shop.root.persistence.Shop
import gogo.gogoshop.global.error.ShopException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ShopValidator {

    fun valid(point: Long, ticketPrice: Long, purchaseQauntity: Int, nowTicketQauntity: Int) {
        if (purchaseQauntity > nowTicketQauntity) {
            throw ShopException("Purchase Ticket Qauntity Is Larger Than Shop ticket Qauntity", HttpStatus.BAD_REQUEST.value())
        }

        val totalTicketPrice = purchaseQauntity * ticketPrice

        if (totalTicketPrice > point) {
            throw ShopException("Purchase Ticket Price Is Higher Than points You Have", HttpStatus.BAD_REQUEST.value())
        }
    }

    fun validShopStatus(shop: Shop) {
        if (shop.isActiveShop == ActiveShopStatus.PENDING) {
            throw ShopException("Shop Is Not Active Now Status Pending, shopId=${shop.shopId}", HttpStatus.BAD_REQUEST.value())
        }
    }
}