package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.cointoss.application.CoinTossReader
import gogo.gogoshop.domain.cointoss.persistence.CoinTossRepository
import gogo.gogoshop.domain.plinko.application.PlinkoReader
import gogo.gogoshop.domain.plinko.persistence.PlinkoRepository
import gogo.gogoshop.domain.shop.receipt.persistence.PurchaseStatus
import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceipt
import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceiptRepository
import gogo.gogoshop.domain.shop.receipt.persistence.TicketType
import gogo.gogoshop.domain.shop.root.persistence.Shop
import gogo.gogoshop.domain.yavarwee.application.YavarweeReader
import gogo.gogoshop.domain.yavarwee.persistence.YavarweeRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ShopProcessor(
    private val yavarweeReader: YavarweeReader,
    private val plinkoReader: PlinkoReader,
    private val coinTossReader: CoinTossReader,
    private val yavarweeRepository: YavarweeRepository,
    private val plinkoRepository: PlinkoRepository,
    private val coinTossRepository: CoinTossRepository,
    private val shopReceiptRepository: ShopReceiptRepository,
) {

    fun minusShopTicketQauntity(shop: Shop, ticketType: TicketType, minusQuantity: Int) {
        if (ticketType == TicketType.PLINKO) {
            val plinko = plinkoReader.read(shop.shopId)
            plinko!!.minusQuantity(minusQuantity)
            plinkoRepository.save(plinko)
        } else if (ticketType == TicketType.COINTOSS) {
            val coinToss = coinTossReader.read(shop.shopId)
            coinToss!!.minusQuantity(minusQuantity)
            coinTossRepository.save(coinToss)
        } else if (ticketType == TicketType.YAVARWEE) {
            val yavarwee = yavarweeReader.read(shop.shopId)
            yavarwee!!.minusQuantity(minusQuantity)
            yavarweeRepository.save(yavarwee)
        }
    }

    fun saveBuyTicketReceipt(
        shopId: Long,
        studentId: Long,
        ticketType: TicketType,
        purchaseQuantity: Int,
        ticketPrice: Int
    ): ShopReceipt {
        val receipt = ShopReceipt(
            shopId = shopId,
            studentId = studentId,
            ticketQauntity = purchaseQuantity,
            ticketPrice = ticketPrice,
            ticketType = ticketType,
            purchaseDate = LocalDateTime.now(),
            purchaseStatus = PurchaseStatus.CONFORMED,
        )

        return shopReceiptRepository.save(receipt)
    }
}