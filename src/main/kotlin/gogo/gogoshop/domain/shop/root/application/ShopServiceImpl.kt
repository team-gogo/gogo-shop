package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.cointoss.application.CoinTossReader
import gogo.gogoshop.domain.plinko.application.PlinkoReader
import gogo.gogoshop.domain.shop.root.application.dto.BuyMiniGameTicketReqDto
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto
import gogo.gogoshop.domain.shop.root.event.TicketShopBuyEvent
import gogo.gogoshop.domain.yavarwee.application.YavarweeReader
import gogo.gogoshop.global.internal.point.api.PointApi
import gogo.gogoshop.global.util.UserContextUtil
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ShopServiceImpl(
    private val shopReader: ShopReader,
    private val coinTossReader: CoinTossReader,
    private val plinkoReader: PlinkoReader,
    private val yavarweeReader: YavarweeReader,
    private val shopMapper: ShopMapper,
    private val pointApi: PointApi,
    private val userUtil: UserContextUtil,
    private val shopValidator: ShopValidator,
    private val shopProcessor: ShopProcessor,
    private val applicationEventPublisher: ApplicationEventPublisher
): ShopService {

    @Transactional(readOnly = true)
    override fun getShopTicketStatus(stageId: Long): ShopTicketStatusResDto {
        val shop = shopReader.readByStageId(stageId)
        val coinToss = coinTossReader.read(shop.shopId)
        val plinko = plinkoReader.read(shop.shopId)
        val yavarwee = yavarweeReader.read(shop.shopId)
        val coinTossResDto = shopMapper.mapCoinToss(coinToss)
        val plinkoResDto = shopMapper.mapPlinko(plinko)
        val yavarweeResDto = shopMapper.mapYavarwee(yavarwee)
        return shopMapper.map(shop.shopId, coinTossResDto, yavarweeResDto, plinkoResDto)
    }

    @Transactional
    override fun buyMiniGameTicket(shopId: Long, buyMiniGameTicketReqDto: BuyMiniGameTicketReqDto) {
        val shop = shopReader.readByShopId(shopId)
        val student = userUtil.getCurrentStudent()
        val pointDto = pointApi.queryPointByStageIdAndStudentId(shop.stageId, student.studentId)
        val miniGameId = shopReader.readShopTicketId(shopId, buyMiniGameTicketReqDto.ticketType)
        val ticketPrice = shopReader.readTicketPrice(shopId, buyMiniGameTicketReqDto.ticketType)
        val nowTicketQauntity = shopReader.readTicketQauntity(shopId, buyMiniGameTicketReqDto.ticketType)
        shopValidator.valid(pointDto.point, ticketPrice, buyMiniGameTicketReqDto.purchaseQuantity, nowTicketQauntity)
        shopProcessor.minusShopTicketQauntity(shop, buyMiniGameTicketReqDto.ticketType, buyMiniGameTicketReqDto.purchaseQuantity)
        val receipt = shopProcessor.saveBuyTicketReceipt(shopId, student.studentId, buyMiniGameTicketReqDto.ticketType, buyMiniGameTicketReqDto.purchaseQuantity, ticketPrice)
        applicationEventPublisher.publishEvent(
            TicketShopBuyEvent(
                id = UUID.randomUUID().toString(),
                studentId = student.studentId,
                shopId = shopId,
                miniGameId = miniGameId,
                ticketType = buyMiniGameTicketReqDto.ticketType,
                purchaseQuantity = buyMiniGameTicketReqDto.purchaseQuantity,
                ticketPrice = ticketPrice,
                shopReceiptId = receipt.shopReceiptId
            )
        )
    }

}