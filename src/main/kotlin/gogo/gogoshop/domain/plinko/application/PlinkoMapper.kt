package gogo.gogoshop.domain.plinko.application

import gogo.gogoshop.domain.plinko.application.dto.PlinkoResDto
import gogo.gogoshop.domain.plinko.persistence.Plinko
import org.springframework.stereotype.Component

@Component
class PlinkoMapper {

    fun map(plinko: Plinko?) =
        plinko?.let {
            PlinkoResDto(
                plinkoId = it.plinkoId,
                ticketPrice = it.ticketPrice,
                ticketQuantity = it.ticketQuantity
            )
        }

}