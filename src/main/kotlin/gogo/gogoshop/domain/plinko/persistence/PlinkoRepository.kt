package gogo.gogoshop.domain.plinko.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface PlinkoRepository: JpaRepository<Plinko, Long> {
}