package gogo.gogoshop.domain.shop.root.application



import gogo.gogoshop.domain.cointoss.application.dto.CoinTossResDto
import gogo.gogoshop.domain.cointoss.persistence.CoinToss
import gogo.gogoshop.domain.plinko.application.dto.PlinkoResDto
import gogo.gogoshop.domain.plinko.persistence.Plinko
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto
import gogo.gogoshop.domain.yavarwee.application.dto.YavarweeResDto
import gogo.gogoshop.domain.yavarwee.persistence.Yavarwee
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

    fun map(coinToss: CoinToss?) =
        coinToss?.let {
            CoinTossResDto(
                coinTossId = it.coinTossId,
                ticketPrice = it.ticketPrice,
                ticketQuantity = it.ticketQauntity
            )
        }

    fun map(plinko: Plinko?) =
        plinko?.let {
            PlinkoResDto(
                plinkoId = it.plinkoId,
                ticketPrice = it.ticketPrice,
                ticketQuantity = it.ticketQauntity
            )
        }

    fun map(yavarwee: Yavarwee?) =
        yavarwee?.let {
            YavarweeResDto(
                yavarweeId = it.yavarweeId,
                ticketPrice = it.ticketPrice,
                ticketQuantity = it.ticketQauntity
            )
        }

}