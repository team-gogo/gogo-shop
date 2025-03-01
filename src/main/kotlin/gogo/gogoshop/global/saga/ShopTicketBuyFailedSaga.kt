package gogo.gogoshop.global.saga

import gogo.gogoshop.domain.cointoss.persistence.CoinTossRepository
import gogo.gogoshop.domain.plinko.persistence.PlinkoRepository
import gogo.gogoshop.domain.shop.receipt.persistence.TicketType
import gogo.gogoshop.domain.shop.root.persistence.ShopRepository
import gogo.gogoshop.domain.yavarwee.persistence.YavarweeRepository
import gogo.gogoshop.global.error.ShopException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ShopTicketBuyFailedSaga(
    private val plinkoRepository: PlinkoRepository,
    private val yavarweeRepository: YavarweeRepository,
    private val coinTossRepository: CoinTossRepository
) {

    @Transactional
    fun cancelledTicketBuy(shopId: Long, ticketType: TicketType, purchaseQuantity: Int) {
        if (ticketType == TicketType.PLINKO) {
            val plinko = plinkoRepository.findByShopId(shopId)
                ?: throw ShopException("Plinko Not Found -- SAGA.cancelledTicketBuy(shopId=$shopId, ticketType=$ticketType)",
                    HttpStatus.NOT_FOUND.value()
                )

            plinko.plusQuantity(purchaseQuantity)
            plinkoRepository.save(plinko)
        } else if (ticketType == TicketType.YAVARWEE) {
            val yavarwee = yavarweeRepository.findByShopId(shopId)
                ?: throw ShopException("Yavarwee Not Found -- SAGA.cancelledTicketBuy(shopId=$shopId, ticketType=$ticketType)",
                    HttpStatus.NOT_FOUND.value()
                )

            yavarwee.plusQuantity(purchaseQuantity)
            yavarweeRepository.save(yavarwee)
        } else {
            val coinToss = coinTossRepository.findByShopId(shopId)
                ?: throw ShopException("CoinToss Not Found -- SAGA.cancelledTicketBuy(shopId=$shopId, ticketType=$ticketType)",
                    HttpStatus.NOT_FOUND.value()
                )

            coinToss.plusQuantity(purchaseQuantity)
            coinTossRepository.save(coinToss)
        }
    }

}