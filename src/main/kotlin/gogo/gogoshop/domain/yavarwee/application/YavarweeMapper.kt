package gogo.gogoshop.domain.yavarwee.application

import gogo.gogoshop.domain.yavarwee.application.dto.YavarweeResDto
import gogo.gogoshop.domain.yavarwee.persistence.Yavarwee
import org.springframework.stereotype.Component

@Component
class YavarweeMapper {

    fun map(yavarwee: Yavarwee?) =
        yavarwee?.let {
            YavarweeResDto(
                yavarweeId = it.yavarweeId,
                ticketPrice = it.ticketPrice,
                ticketQuantity = it.ticketQauntity
            )
        }
}