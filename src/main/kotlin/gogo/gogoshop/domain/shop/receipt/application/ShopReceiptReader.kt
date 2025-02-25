package gogo.gogoshop.domain.shop.receipt.application

import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceiptRepository
import org.springframework.stereotype.Component

@Component
class ShopReceiptReader(
    private val shopReceiptRepository: ShopReceiptRepository
) {

    fun read(shopId: Long, studentId: Long) =
        shopReceiptRepository.findByShopIdAndStudentId(shopId, studentId)

    fun readReceiptCount(shopId: Long, studentId: Long) =
        shopReceiptRepository.countByShopIdAndStudentId(shopId, studentId)


}