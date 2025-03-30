package gogo.gogoshop.domain.cointoss.application

import gogo.gogoshop.domain.cointoss.persistence.CoinToss
import gogo.gogoshop.domain.cointoss.persistence.CoinTossRepository
import org.springframework.stereotype.Component

@Component
class CoinTossReader(
    private val coinTossRepository: CoinTossRepository
) {

    fun readForWrite(shopId: Long): CoinToss? =
        coinTossRepository.findByShopIdForWrite(shopId)

    fun readCoinTossTicketId(shopId: Long): Long {
        val coinToss = coinTossRepository.findByShopIdForWrite(shopId)
        return coinToss!!.coinTossId
    }

    fun readCoinTossTicketPrice(shopId: Long): Long {
        val coinToss = readForWrite(shopId)
        return coinToss!!.ticketPrice
    }

    fun readCoinTossTicketQauntity(shopId: Long): Int {
        val coinToss = readForWrite(shopId)
        return coinToss!!.ticketQuantity
    }

}