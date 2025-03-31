package gogo.gogoshop.domain.yavarwee.application

import gogo.gogoshop.domain.yavarwee.persistence.Yavarwee
import gogo.gogoshop.domain.yavarwee.persistence.YavarweeRepository
import org.springframework.stereotype.Component

@Component
class YavarweeReader(
    private val yavarweeRepository: YavarweeRepository
) {

    fun readForWrite(shopId: Long): Yavarwee? =
        yavarweeRepository.findByShopIdForWrite(shopId)

    fun read(shopId: Long): Yavarwee? =
        yavarweeRepository.findByShopId(shopId)

    fun readYavarweeTicketId(shopId: Long): Long {
        val yavarwee = read(shopId)
        return yavarwee!!.yavarweeId
    }

    fun readYavarweeTicketPrice(shopId: Long): Long {
        val yavarwee = read(shopId)
        return yavarwee!!.ticketPrice
    }

    fun readYavarweeTicketQuantity(shopId: Long): Int {
        val yavarwee = read(shopId)
        return yavarwee!!.ticketQuantity
    }
}