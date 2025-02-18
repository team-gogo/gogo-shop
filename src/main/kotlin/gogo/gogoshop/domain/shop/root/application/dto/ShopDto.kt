package gogo.gogoshop.domain.shop.root.application.dto

import gogo.gogoshop.domain.cointoss.application.dto.CoinTossResDto
import gogo.gogoshop.domain.plinko.application.dto.PlinkoResDto
import gogo.gogoshop.domain.yavarwee.application.dto.YavarweeResDto

data class ShopTicketStatusResDto(
    val shopId: Long,
    val coinToss: CoinTossResDto?,
    val yavarwee: YavarweeResDto?,
    val plinko: PlinkoResDto?
)