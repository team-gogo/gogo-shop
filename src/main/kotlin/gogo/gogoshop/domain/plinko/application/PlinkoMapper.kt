package gogo.gogoshop.domain.plinko.application

import gogo.gogoshop.domain.plinko.application.dto.PlinkoResDto
import gogo.gogoshop.domain.plinko.persistence.Plinko
import org.springframework.stereotype.Component

@Component
class PlinkoMapper {

    fun map(plinko: Plinko) =
        PlinkoResDto(
            plinkoId = plinko.plinkoId,
            ticketPrice = plinko.ticketPrice,
            ticketQuantity = plinko.ticketQauntity
        )

}