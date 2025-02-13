package gogo.gogoshop.domain.yavarwee.application

import gogo.gogoshop.domain.yavarwee.persistence.Yavarwee
import gogo.gogoshop.domain.yavarwee.persistence.YavarweeRepository
import org.springframework.stereotype.Component

@Component
class YavarweeReader(
    private val yavarweeRepository: YavarweeRepository
) {

    fun read(shopId: Long): Yavarwee =
        yavarweeRepository.findByShopId(shopId)

}