package gogo.gogoshop.domain.cointoss.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface CoinTossRepository: JpaRepository<CoinToss, Long> {
}