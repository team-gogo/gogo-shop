package gogo.gogoshop.domain.shop.root.application



import gogo.gogoshop.domain.cointoss.application.dto.CoinTossResDto
import gogo.gogoshop.domain.plinko.application.dto.PlinkoResDto
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto
import gogo.gogoshop.domain.yavarwee.application.dto.YavarweeResDto
import org.springframework.stereotype.Component

@Component
class ShopMapper {

    fun map(
        shopId: Long,
        coinTossDto: CoinTossResDto?,
        yavarweeDto: YavarweeResDto?,
        plinkoDto: PlinkoResDto?
    ) =
        ShopTicketStatusResDto(
            shopId = shopId,
            coinToss = coinTossDto,
            yavarwee = yavarweeDto,
            plinko = plinkoDto
        )

}