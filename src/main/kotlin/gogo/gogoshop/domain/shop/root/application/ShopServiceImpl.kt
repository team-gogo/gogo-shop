package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.cointoss.application.CoinTossReader
import gogo.gogoshop.domain.plinko.application.PlinkoReader
import gogo.gogoshop.domain.shop.root.application.dto.ShopTicketStatusResDto
import gogo.gogoshop.domain.yavarwee.application.YavarweeReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShopServiceImpl(
    private val shopReader: ShopReader,
    private val coinTossReader: CoinTossReader,
    private val plinkoReader: PlinkoReader,
    private val yavarweeReader: YavarweeReader,
    private val shopMapper: ShopMapper
): ShopService {

    @Transactional(readOnly = true)
    override fun getShopTicketStatus(stageId: Long): ShopTicketStatusResDto {
        val shop = shopReader.read(stageId)
        val coinToss = coinTossReader.read(shop.shopId)
        val plinko = plinkoReader.read(shop.shopId)
        val yavarwee = yavarweeReader.read(shop.shopId)
        val coinTossResDto = shopMapper.mapCoinToss(coinToss)
        val plinkoResDto = shopMapper.mapPlinko(plinko)
        val yavarweeResDto = shopMapper.mapYavarwee(yavarwee)
        return shopMapper.map(shop.shopId, coinTossResDto, yavarweeResDto, plinkoResDto)
    }

}