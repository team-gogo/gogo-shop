package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.shop.root.persistence.ShopRepository
import gogo.gogoshop.global.error.ShopException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ShopReader(
    private val shopRepository: ShopRepository
) {

    fun read(stageId: Long) =
        shopRepository.findByStageId(stageId)
            ?: throw ShopException("Shop Not Found", HttpStatus.NOT_FOUND.value())

}