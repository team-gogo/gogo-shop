package gogo.gogoshop.domain.yavarwee.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface YavarweeRepository: JpaRepository<Yavarwee, Long> {
    fun findByShopId(shopId: Long): Yavarwee
}