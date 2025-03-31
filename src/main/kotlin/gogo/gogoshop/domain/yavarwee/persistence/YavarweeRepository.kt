package gogo.gogoshop.domain.yavarwee.persistence

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface YavarweeRepository: JpaRepository<Yavarwee, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT y FROM Yavarwee y WHERE y.shopId = :shopId")
    fun findByShopIdForWrite(shopId: Long): Yavarwee?

    fun findByShopId(shopId: Long): Yavarwee?
}