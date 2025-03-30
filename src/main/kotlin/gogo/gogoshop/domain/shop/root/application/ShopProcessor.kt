package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.cointoss.application.CoinTossReader
import gogo.gogoshop.domain.cointoss.persistence.CoinToss
import gogo.gogoshop.domain.cointoss.persistence.CoinTossRepository
import gogo.gogoshop.domain.plinko.application.PlinkoReader
import gogo.gogoshop.domain.plinko.persistence.Plinko
import gogo.gogoshop.domain.plinko.persistence.PlinkoRepository
import gogo.gogoshop.domain.shop.receipt.persistence.PurchaseStatus
import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceipt
import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceiptRepository
import gogo.gogoshop.domain.shop.receipt.persistence.TicketType
import gogo.gogoshop.domain.shop.root.persistence.ActiveShopStatus
import gogo.gogoshop.domain.shop.root.persistence.Shop
import gogo.gogoshop.domain.shop.root.persistence.ShopRepository
import gogo.gogoshop.domain.yavarwee.application.YavarweeReader
import gogo.gogoshop.domain.yavarwee.persistence.Yavarwee
import gogo.gogoshop.domain.yavarwee.persistence.YavarweeRepository
import gogo.gogoshop.global.error.ShopException
import gogo.gogoshop.global.kafka.consumer.dto.StageCreateOfficialEvent
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
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
    private val shopReader: ShopReader,
    private val shopRepository: ShopRepository,
) {

    fun minusShopTicketQauntity(shop: Shop, ticketType: TicketType, minusQuantity: Int) {
        if (ticketType == TicketType.PLINKO) {
            val plinko = plinkoReader.read(shop.shopId)
            plinko!!.minusQuantity(minusQuantity)
            plinkoRepository.save(plinko)
        } else if (ticketType == TicketType.COINTOSS) {
            val coinToss = coinTossReader.readForWrite(shop.shopId)
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
        ticketPrice: Long
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

    @Transactional
    fun shopActiveToAble(stageId: Long) {
        val shop = shopReader.readByStageId(stageId)

        shop.changeActiveShopStatus()

        shopRepository.save(shop)
    }

    fun shopActiveToPendingAndTicketSetting(event: StageCreateOfficialEvent) {
        val isExistsShop = shopRepository.existsByStageId(event.stageId)
        if (isExistsShop) {
            throw ShopException("Already Exists Shop", HttpStatus.CONFLICT.value())
        }

        val shop = Shop(
            stageId = event.stageId,
            isActiveShop = ActiveShopStatus.PENDING
        )

        shopRepository.save(shop)

        if (event.shop.coinToss.isActive) {
            val coinTossDto = event.shop.coinToss

            val coinToss = CoinToss(
                shopId = shop.shopId,
                ticketPrice = coinTossDto.price!!,
                ticketQuantity = coinTossDto.quantity!!
            )

            coinTossRepository.save(coinToss)
        }

        if (event.shop.plinko.isActive) {
            val plinkoDto = event.shop.plinko

            val plinko = Plinko(
                shopId = shop.shopId,
                ticketPrice = plinkoDto.price!!,
                ticketQuantity = plinkoDto.quantity!!
            )

            plinkoRepository.save(plinko)
        }

        if (event.shop.yavarwee.isActive) {
            val yavarweeDto = event.shop.yavarwee

            val yavarwee = Yavarwee(
                shopId = shop.shopId,
                ticketPrice = yavarweeDto.price!!,
                ticketQuantity = yavarweeDto.quantity!!
            )

            yavarweeRepository.save(yavarwee)
        }
    }
}