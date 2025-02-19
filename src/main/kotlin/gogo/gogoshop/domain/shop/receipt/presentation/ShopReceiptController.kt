package gogo.gogoshop.domain.shop.receipt.presentation

import gogo.gogoshop.domain.shop.receipt.application.ShopReceiptService
import gogo.gogoshop.domain.shop.receipt.application.dto.ReceiptResDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shop/receipt")
class ShopReceiptController(
    private val shopReceiptService: ShopReceiptService
) {

    @GetMapping("/{shop_id}")
    fun getReceipt(@PathVariable("shop_id") shopId: Long): ResponseEntity<ReceiptResDto> {
        val response = shopReceiptService.getShopReceipt(shopId)
        return ResponseEntity.ok(response)
    }
}