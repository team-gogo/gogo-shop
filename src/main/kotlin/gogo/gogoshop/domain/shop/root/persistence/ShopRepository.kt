package gogo.gogoshop.domain.shop.root.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository: JpaRepository<Shop, Long> {
}