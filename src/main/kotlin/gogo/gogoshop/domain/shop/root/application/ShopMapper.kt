package gogo.gogoshop.domain.shop.root.application



import gogo.gogoshop.domain.cointoss.application.CoinTossMapper
import gogo.gogoshop.domain.cointoss.application.dto.CoinTossResDto
import gogo.gogoshop.domain.cointoss.persistence.CoinToss
import gogo.gogoshop.domain.plinko.application.PlinkoMapper
import gogo.gogoshop.domain.plinko.application.dto.PlinkoResDto
import gogo.gogoshop.domain.plinko.persistence.Plinko
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto
import gogo.gogoshop.domain.yavarwee.application.YavarweeMapper
import gogo.gogoshop.domain.yavarwee.application.dto.YavarweeResDto
import gogo.gogoshop.domain.yavarwee.persistence.Yavarwee
import org.springframework.stereotype.Component

@Component
class ShopMapper(
    private val coinTossMapper: CoinTossMapper,
    private val plinkoMapper: PlinkoMapper,
    private val yavarweeMapper: YavarweeMapper,
) {

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

    fun mapCoinToss(coinToss: CoinToss?) =
        coinTossMapper.map(coinToss)

    fun mapPlinko(plinko: Plinko?) =
        plinkoMapper.map(plinko)

    fun mapYavarwee(yavarwee: Yavarwee?) =
        yavarweeMapper.map(yavarwee)

}