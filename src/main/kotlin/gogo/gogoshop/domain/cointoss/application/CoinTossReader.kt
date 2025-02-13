package gogo.gogoshop.domain.cointoss.application

import gogo.gogoshop.domain.cointoss.persistence.CoinToss
import gogo.gogoshop.domain.cointoss.persistence.CoinTossRepository
import org.springframework.stereotype.Component

@Component
class CoinTossReader(
    private val coinTossRepository: CoinTossRepository
) {

    fun read(shopId: Long): CoinToss =
        coinTossRepository.findByShopId(shopId)

}