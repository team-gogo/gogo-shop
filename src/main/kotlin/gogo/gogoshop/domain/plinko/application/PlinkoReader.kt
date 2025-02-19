package gogo.gogoshop.domain.plinko.application

import gogo.gogoshop.domain.plinko.persistence.Plinko
import gogo.gogoshop.domain.plinko.persistence.PlinkoRepository
import org.springframework.stereotype.Component

@Component
class PlinkoReader(
    private val plinkoRepository: PlinkoRepository
) {

    fun read(shopId: Long): Plinko? =
        plinkoRepository.findByShopId(shopId)

}