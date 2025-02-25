package gogo.gogoshop.domain.shop.receipt.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface ShopReceiptRepository: JpaRepository<ShopReceipt, Long> {
    fun findByShopIdAndStudentId(shopId: Long, studentId: Long): List<ShopReceipt>
    fun countByShopIdAndStudentId(shopId: Long, studentId: Long): Int
}