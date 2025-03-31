package gogo.gogoshop.domain.cointoss.persistence

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface CoinTossRepository: JpaRepository<CoinToss, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CoinToss c WHERE c.shopId = :shopId")
    fun findByShopIdForWrite(shopId: Long): CoinToss?

    fun findByShopId(shopId: Long): CoinToss?
}