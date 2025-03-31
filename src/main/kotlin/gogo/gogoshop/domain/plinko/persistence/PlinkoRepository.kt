package gogo.gogoshop.domain.plinko.persistence

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface PlinkoRepository: JpaRepository<Plinko, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Plinko p WHERE p.shopId = :shopId")
    fun findByShopIdForWrite(shopId: Long): Plinko?

    fun findByShopId(shopId: Long): Plinko?

}