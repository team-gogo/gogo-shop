package gogo.gogoshop.global.publusher

interface TransactionEventPublisher {
    fun publishEvent(topic: String, key: String, event: Any)
}