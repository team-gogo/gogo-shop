package gogo.gogoshop.domain.shop.root.application.dto

import gogo.gogoshop.domain.cointoss.application.dto.CoinTossResDto
import gogo.gogoshop.domain.plinko.application.dto.PlinkoResDto
import gogo.gogoshop.domain.shop.receipt.persistence.TicketType
import gogo.gogoshop.domain.yavarwee.application.dto.YavarweeResDto
import jakarta.validation.constraints.NotNull

data class ShopTicketStatusResDto(
    val shopId: Long,
    val coinToss: CoinTossResDto?,
    val yavarwee: YavarweeResDto?,
    val plinko: PlinkoResDto?
)

data class BuyMiniGameTicketReqDto(
    @NotNull
    val purchaseQuantity: Int,
    @NotNull
    val ticketType: TicketType
)