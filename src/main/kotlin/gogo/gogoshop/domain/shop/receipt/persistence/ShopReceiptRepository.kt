package gogo.gogoshop.domain.shop.receipt.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface ShopReceiptRepository: JpaRepository<ShopReceipt, Long> {
}