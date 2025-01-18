package gogo.gogoshop.domain.shop.receipt.persistence

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "tbl_shop_receipt")
class ShopReceipt(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_receipt_id")
    val shopReceiptId: Long = 0,

    @Column(name = "shop_id", nullable = false, unique = true)
    val shopId: Long,

    @Column(name = "student_id", nullable = false, unique = true)
    val studentId: Long,

    @Column(name = "ticket_qauntity", nullable = false)
    val ticketQauntity: Int,

    @Column(name = "ticket_price", nullable = false)
    val ticketPrice: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type", nullable = false)
    val ticketType: TicketType,

    @Column(name = "purchase_date", nullable = false)
    val purchaseDate: LocalDate
) {
}

enum class TicketType {
    COINTOSS, TAVARWEE, PLINKO
}