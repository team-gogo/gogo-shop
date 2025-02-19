package gogo.gogoshop.domain.shop.root.presentation

import gogo.gogoshop.domain.shop.root.application.ShopService
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
}