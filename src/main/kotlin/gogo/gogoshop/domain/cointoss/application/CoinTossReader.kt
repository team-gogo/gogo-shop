package gogo.gogoshop.domain.cointoss.application

import gogo.gogoshop.domain.cointoss.persistence.CoinToss
import gogo.gogoshop.domain.cointoss.persistence.CoinTossRepository
import org.springframework.stereotype.Component

@Component
class CoinTossReader(
    private val coinTossRepository: CoinTossRepository
) {

    fun read(shopId: Long): CoinToss? =
        coinTossRepository.findByShopId(shopId)

    fun readCoinTossTicketId(shopId: Long): Long {
        val coinToss = coinTossRepository.findByShopId(shopId)
        return coinToss!!.coinTossId
    }

    fun readCoinTossTicketPrice(shopId: Long): Long {
        val coinToss = read(shopId)
        return coinToss!!.ticketPrice
    }

    fun readCoinTossTicketQauntity(shopId: Long): Int {
        val coinToss = read(shopId)
        return coinToss!!.ticketQuantity
    }

}