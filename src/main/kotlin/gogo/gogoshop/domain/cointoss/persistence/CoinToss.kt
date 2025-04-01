package gogo.gogoshop.domain.cointoss.persistence

import jakarta.persistence.*

@Entity
@Table(name = "tbl_coin_toss")
class CoinToss(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coin_toss_id")
    val coinTossId: Long = 0,

    @Column(name = "shop_id", nullable = false, unique = true)
    val shopId: Long,

    @Column(name = "ticket_price")
    val ticketPrice: Long,

    @Column(name = "ticket_qauntity")
    var ticketQuantity: Int
) {

    fun minusQuantity(minusTicketQuantity: Int) {
        ticketQuantity -= minusTicketQuantity
    }

    fun plusQuantity(plusQuantity: Int) {
        ticketQuantity += plusQuantity
    }

}