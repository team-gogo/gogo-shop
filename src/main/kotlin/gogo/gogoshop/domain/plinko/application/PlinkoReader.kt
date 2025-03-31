package gogo.gogoshop.domain.plinko.application

import gogo.gogoshop.domain.plinko.persistence.Plinko
import gogo.gogoshop.domain.plinko.persistence.PlinkoRepository
import org.springframework.stereotype.Component

@Component
class PlinkoReader(
    private val plinkoRepository: PlinkoRepository
) {

    fun readForWrite(shopId: Long): Plinko? =
        plinkoRepository.findByShopIdForWrite(shopId)

    fun read(shopId: Long): Plinko? =
        plinkoRepository.findByShopId(shopId)

    fun readPlinkoTicketId(shopId: Long): Long {
        val plinko = plinkoRepository.findByShopId(shopId)
        return plinko!!.plinkoId
    }

    fun readPlinkoTicketPrice(shopId: Long): Long {
        val plinko = readForWrite(shopId)
        return plinko!!.ticketPrice
    }

    fun readPlinkoTicketQauntity(shopId: Long): Int {
        val plinko = read(shopId)
        return plinko!!.ticketQuantity
    }
}