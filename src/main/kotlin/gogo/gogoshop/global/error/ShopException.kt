package gogo.gogoshop.global.error

open class ShopException(
    override val message: String,
    val status: Int
) : RuntimeException(message)
