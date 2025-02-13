package gogo.gogoshop.domain.cointoss.application

import gogo.gogoshop.domain.cointoss.application.dto.CoinTossResDto
import gogo.gogoshop.domain.cointoss.persistence.CoinToss
import org.springframework.stereotype.Component

@Component
class CoinTossMapper {

    fun map(coinToss: CoinToss) =
        CoinTossResDto(
            coinTossId = coinToss.coinTossId,
            ticketPrice = coinToss.ticketPrice,
            ticketQuantity = coinToss.ticketQauntity
        )

}
