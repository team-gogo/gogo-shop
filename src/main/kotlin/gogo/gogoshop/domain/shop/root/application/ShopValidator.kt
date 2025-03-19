package gogo.gogoshop.domain.shop.root.application

import gogo.gogoshop.domain.shop.root.persistence.ActiveShopStatus
import gogo.gogoshop.domain.shop.root.persistence.Shop
import gogo.gogoshop.global.error.ShopException
import gogo.gogoshop.global.internal.participant.api.ParticipantApi
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ShopValidator(
    private val participantApi: ParticipantApi
) {

    fun valid(point: Long, ticketPrice: Long, purchaseQauntity: Int, nowTicketQauntity: Int) {
        if (purchaseQauntity > nowTicketQauntity) {
            throw ShopException("Purchase Ticket Qauntity Is Larger Than Shop ticket Qauntity", HttpStatus.BAD_REQUEST.value())
        }

        val totalTicketPrice = purchaseQauntity * ticketPrice

        if (totalTicketPrice > point) {
            throw ShopException("Purchase Ticket Price Is Higher Than points You Have", HttpStatus.BAD_REQUEST.value())
        }
    }

    fun validShopStatus(shop: Shop) {
        if (shop.isActiveShop == ActiveShopStatus.PENDING) {
            throw ShopException("Shop Is Not Active Now Status Pending, shopId=${shop.shopId}", HttpStatus.BAD_REQUEST.value())
        }
    }

    fun validStage(stageId: Long, studentId: Long) {
        val isParticipant = participantApi.isParticipantByStageIdAndStudentId(stageId, studentId).isParticipant

        if (isParticipant.not()) {
            throw ShopException("해당 유저는 스테이지에 참여 중이지 않습니다, stageId=$stageId, studentId=$studentId", HttpStatus.FORBIDDEN.value())
        }
    }
}