package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.shop.root.application.dto.BuyMiniGameTicketReqDto
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto

interface ShopService {
    fun getShopTicketStatus(stageId: Long): ShopTicketStatusResDto
    fun buyMiniGameTicket(shopId: Long, buyMiniGameTicketReqDto: BuyMiniGameTicketReqDto)
}