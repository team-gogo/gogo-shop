package gogo.gogoshop.domain.shop.receipt.application

import gogo.gogoshop.domain.shop.receipt.application.dto.ReceiptResDto
import gogo.gogoshop.global.util.UserContextUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShopReceiptServiceImpl(
    private val shopReceiptReader: ShopReceiptReader,
    private val shopReceiptMapper: ShopReceiptMapper,
    private val userUtil: UserContextUtil
): ShopReceiptService {

    @Transactional(readOnly = true)
    override fun getShopReceipt(shopId: Long): ReceiptResDto {
        val student = userUtil.getCurrentStudent()
        val shopReceiptList = shopReceiptReader.read(shopId, student.studentId)
        val shopTicketReceiptDtoList = shopReceiptMapper.map(shopReceiptList)
        return ReceiptResDto(shopTicketReceiptDtoList)
    }
}