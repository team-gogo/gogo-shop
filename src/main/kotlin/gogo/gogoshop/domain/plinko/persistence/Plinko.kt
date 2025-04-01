package gogo.gogoshop.domain.plinko.persistence

import jakarta.persistence.*

@Entity
@Table(name = "tbl_plinko")
class Plinko(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plinko_id")
    val plinkoId: Long = 0,

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