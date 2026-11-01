package gogo.gogoshop

import gogo.gogoshop.domain.shop.receipt.persistence.PurchaseStatus
import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceipt
import gogo.gogoshop.domain.shop.receipt.persistence.ShopReceiptRepository
import gogo.gogoshop.domain.shop.receipt.persistence.TicketType
import gogo.gogoshop.domain.shop.root.persistence.ActiveShopStatus
import gogo.gogoshop.domain.shop.root.persistence.Shop
import gogo.gogoshop.domain.shop.root.persistence.ShopRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.JdbcTemplate
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnabledIfEnvironmentVariable(named = "DATABASE_URL", matches = "jdbc:postgresql:.*")
class PostgresSchemaTest @Autowired constructor(
    private val shops: ShopRepository,
    private val receipts: ShopReceiptRepository,
    private val jdbc: JdbcTemplate,
) {
    @Test
    fun `flyway schema validates shop receipts and unique stages`() {
        val shop = shops.saveAndFlush(Shop(stageId = 901L, isActiveShop = ActiveShopStatus.PENDING))
        val receipt = receipts.saveAndFlush(
            ShopReceipt(
                shopId = shop.shopId,
                studentId = 902L,
                ticketQauntity = 2,
                ticketPrice = 15L,
                ticketType = TicketType.COINTOSS,
                purchaseDate = LocalDateTime.now(),
                purchaseStatus = PurchaseStatus.CONFORMED,
            )
        )

        assertTrue(shop.shopId > 0)
        assertEquals(shop.shopId, shops.findByStageId(901L)?.shopId)
        assertEquals(receipt.shopReceiptId, receipts.findByShopIdAndStudentId(shop.shopId, 902L).single().shopReceiptId)

        assertFailsWith<DataIntegrityViolationException> {
            jdbc.update("INSERT INTO tbl_shop(stage_id, is_active_game) VALUES (?, ?)", 901L, "ABLE")
        }
    }
}
