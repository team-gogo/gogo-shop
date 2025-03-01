package gogo.gogoshop.domain.shop.root.presentation

import gogo.gogoshop.domain.shop.root.application.ShopService
import gogo.gogoshop.domain.shop.root.application.dto.BuyMiniGameTicketReqDto
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shop")
class ShopController(
    private val shopService: ShopService
) {

    @GetMapping("/{stage_id}")
    fun getShopTicketStatus(@PathVariable("stage_id") stageId: Long): ResponseEntity<ShopTicketStatusResDto> {
        val response = shopService.getShopTicketStatus(stageId)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{shop_id}")
    fun buyMiniGameTicket(
        @PathVariable("shop_id") shopId: Long,
        @RequestBody @Valid buyMiniGameTicketReqDto: BuyMiniGameTicketReqDto
    ): ResponseEntity<Void> {
        shopService.buyMiniGameTicket(shopId, buyMiniGameTicketReqDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}