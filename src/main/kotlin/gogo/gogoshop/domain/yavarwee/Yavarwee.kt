package gogo.gogoshop.domain.yavarwee

import jakarta.persistence.*

@Entity
@Table(name = "tbl_yavarwee")
class Yavarwee(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yavarwee_id")
    val yavarweeId: Long = 0,

    @Column(name = "shop_id", nullable = false, unique = true)
    val shopId: Long,

    @Column(name = "ticket_price")
    val ticketPrice: Int,

    @Column(name = "ticket_qauntity")
    val ticketQauntity: Int
) {
}