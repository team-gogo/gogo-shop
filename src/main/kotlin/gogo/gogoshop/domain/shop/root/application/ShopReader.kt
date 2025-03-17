package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.cointoss.application.CoinTossReader
import gogo.gogoshop.domain.plinko.application.PlinkoReader
import gogo.gogoshop.domain.shop.receipt.persistence.TicketType
import gogo.gogoshop.domain.shop.root.persistence.ShopRepository
import gogo.gogoshop.domain.yavarwee.application.YavarweeReader
import gogo.gogoshop.global.error.ShopException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ShopReader(
    private val shopRepository: ShopRepository,
    private val coinTossReader: CoinTossReader,
    private val plinkoReader: PlinkoReader,
    private val yavarweeReader: YavarweeReader,
) {

    fun readByStageId(stageId: Long) =
        shopRepository.findByStageId(stageId)
            ?: throw ShopException("Shop Not Found, stageId = $stageId", HttpStatus.NOT_FOUND.value())

    fun readByShopId(shopId: Long) =
        shopRepository.findByIdOrNull(shopId)
            ?: throw ShopException("Shop Not Found, shopId = $shopId", HttpStatus.NOT_FOUND.value())

    fun readShopTicketId(shopId: Long, ticketType: TicketType): Long {
        return when(ticketType) {
            TicketType.PLINKO -> plinkoReader.readPlinkoTicketId(shopId)
            TicketType.COINTOSS -> coinTossReader.readCoinTossTicketId(shopId)
            TicketType.YAVARWEE -> yavarweeReader.readYavarweeTicketId(shopId)
        }
    }

    fun readTicketPrice(shopId: Long, ticketType: TicketType): Int {
        return when(ticketType) {
            TicketType.PLINKO -> plinkoReader.readPlinkoTicketPrice(shopId)
            TicketType.COINTOSS -> coinTossReader.readCoinTossTicketPrice(shopId)
            TicketType.YAVARWEE -> yavarweeReader.readYavarweeTicketPrice(shopId)
        }
    }

    fun readTicketQauntity(shopId: Long, ticketType: TicketType): Int {
        return when(ticketType) {
            TicketType.PLINKO -> plinkoReader.readPlinkoTicketQauntity(shopId)
            TicketType.COINTOSS -> coinTossReader.readCoinTossTicketQauntity(shopId)
            TicketType.YAVARWEE -> coinTossReader.readCoinTossTicketQauntity(shopId)
        }
    }
}
