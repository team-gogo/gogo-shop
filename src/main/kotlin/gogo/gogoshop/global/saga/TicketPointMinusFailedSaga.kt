package gogo.gogoshop.global.saga

import gogo.gogoshop.domain.cointoss.persistence.CoinTossRepository
import gogo.gogoshop.domain.plinko.persistence.PlinkoRepository
import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceiptRepository
import gogo.gogoshop.domain.shop.receipt.persistence.TicketType
import gogo.gogoshop.domain.yavarwee.persistence.YavarweeRepository
import gogo.gogoshop.global.error.ShopException
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class TicketPointMinusFailedSaga(
    private val plinkoRepository: PlinkoRepository,
    private val yavarweeRepository: YavarweeRepository,
    private val coinTossRepository: CoinTossRepository,
    private val shopReceiptRepository: ShopReceiptRepository
) {

    @Transactional
    fun rollbackBoughtTicket(miniGameId: Long, ticketType: TicketType, shopReceiptId: Long) {
        if (ticketType == TicketType.PLINKO) {

            val plinko = plinkoRepository.findByIdOrNull(miniGameId)
                ?: throw ShopException("Plinko Not Found -- SAGA.rollbackBoughtTicket(miniGameId=$miniGameId, ticketType=$ticketType, shopReceiptId=$shopReceiptId)",
                    HttpStatus.NOT_FOUND.value()
                )

            val shopReceipt = shopReceiptRepository.findByIdOrNull(shopReceiptId)
                ?: throw ShopException("ShopReceipt Not Found -- SAGA.rollbackBoughtTicket(miniGameId=$miniGameId, ticketType=$ticketType, shopReceiptId=$shopReceiptId)",
                    HttpStatus.NOT_FOUND.value()
                )


            plinko.plusQuantity(shopReceipt.ticketQauntity)
            plinkoRepository.save(plinko)
            shopReceiptRepository.deleteById(miniGameId)
        } else if (ticketType == TicketType.YAVARWEE) {

            val yavarwee = yavarweeRepository.findByIdOrNull(miniGameId)
                ?: throw ShopException("Yavarwee Not Found -- SAGA.rollbackBoughtTicket(miniGameId=$miniGameId, ticketType=$ticketType, shopReceiptId=$shopReceiptId)",
                    HttpStatus.NOT_FOUND.value()
                )

            val shopReceipt = shopReceiptRepository.findByIdOrNull(shopReceiptId)
                ?: throw ShopException("ShopReceipt Not Found -- SAGA.rollbackBoughtTicket(miniGameId=$miniGameId, ticketType=$ticketType, shopReceiptId=$shopReceiptId)",
                    HttpStatus.NOT_FOUND.value()
                )

            yavarwee.plusQuantity(shopReceipt.ticketQauntity)
            yavarweeRepository.save(yavarwee)
            shopReceiptRepository.deleteById(miniGameId)
        } else {

            val coinToss = coinTossRepository.findByIdOrNull(miniGameId)
                ?: throw ShopException("CoinToss Not Found -- SAGA.rollbackBoughtTicket(miniGameId=$miniGameId, ticketType=$ticketType, shopReceiptId=$shopReceiptId)",
                    HttpStatus.NOT_FOUND.value()
                )

            val shopReceipt = shopReceiptRepository.findByIdOrNull(shopReceiptId)
                ?: throw ShopException("ShopReceipt Not Found -- SAGA.rollbackBoughtTicket(miniGameId=$miniGameId, ticketType=$ticketType, shopReceiptId=$shopReceiptId)",
                    HttpStatus.NOT_FOUND.value()
                )

            coinToss.plusQuantity(shopReceipt.ticketQauntity)
            coinTossRepository.save(coinToss)
            shopReceiptRepository.deleteById(miniGameId)
        }
    }

}