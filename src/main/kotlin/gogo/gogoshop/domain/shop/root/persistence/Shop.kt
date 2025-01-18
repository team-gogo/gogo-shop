package gogo.gogoshop.domain.shop.root.persistence

import jakarta.persistence.*

@Entity
@Table(name = "tbl_shop")
class Shop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    val shopId: Long = 0,

    @Column(name = "stage_id", nullable = false, unique = true)
    val stageId: Long
) {
}