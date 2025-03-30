package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.cointoss.application.CoinTossReader
import gogo.gogoshop.domain.plinko.application.PlinkoReader
import gogo.gogoshop.domain.shop.root.application.dto.BuyMiniGameTicketReqDto
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto
import gogo.gogoshop.domain.shop.root.event.TicketShopBuyEvent
import gogo.gogoshop.domain.yavarwee.application.YavarweeReader
import gogo.gogoshop.global.internal.point.api.StageApi
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
    private val stageApi: StageApi,
    private val userUtil: UserContextUtil,
    private val shopValidator: ShopValidator,
    private val shopProcessor: ShopProcessor,
    private val applicationEventPublisher: ApplicationEventPublisher
): ShopService {

    @Transactional(readOnly = true)
    override fun getShopTicketStatus(stageId: Long): ShopTicketStatusResDto {
        val student = userUtil.getCurrentStudent()
        val shop = shopReader.readByStageId(stageId)
        shopValidator.validStage(shop.stageId, student.studentId)
        shopValidator.validShopStatus(shop)
        val coinToss = coinTossReader.readForWrite(shop.shopId)
        val plinko = plinkoReader.readForWrite(shop.shopId)
        val coinTossResDto = shopMapper.mapCoinToss(coinToss)
        val plinkoResDto = shopMapper.mapPlinko(plinko)
        val yavarweeResDto = shopMapper.mapYavarwee(yavarwee)
        return shopMapper.map(shop.shopId, coinTossResDto, yavarweeResDto, plinkoResDto)
    }

    @Transactional
    override fun buyMiniGameTicket(shopId: Long, buyMiniGameTicketReqDto: BuyMiniGameTicketReqDto) {
        val student = userUtil.getCurrentStudent()
        val shop = shopReader.readByShopId(shopId)
        shopValidator.validStage(shop.stageId, student.studentId)
        shopValidator.validShopStatus(shop)
        val pointDto = stageApi.queryPointByStageIdAndStudentId(shop.stageId, student.studentId)
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
                stageId = shop.stageId,
                shopMiniGameId = miniGameId,
                ticketType = buyMiniGameTicketReqDto.ticketType,
                purchaseQuantity = buyMiniGameTicketReqDto.purchaseQuantity,
                ticketPrice = ticketPrice,
                shopReceiptId = receipt.shopReceiptId,
            )
        )
    }

}