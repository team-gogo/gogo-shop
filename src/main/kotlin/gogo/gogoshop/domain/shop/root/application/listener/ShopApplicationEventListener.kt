package gogo.gogoshop.domain.shop.root.application.listener

import gogo.gogoshop.domain.shop.root.event.TicketShopBuyEvent
import gogo.gogoshop.global.kafka.publisher.ShopPublisher
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ShopApplicationEventListener(
    private val shopPublisher: ShopPublisher
) {

    private val log = LoggerFactory.getLogger(this::class.java.simpleName)

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun shopTicketBuyEvent(event: TicketShopBuyEvent) {
        with(event) {
            log.info("published shop ticket buy application event: {}", id)
            shopPublisher.publishShopTicketBuyEvent(event)
        }
    }
}