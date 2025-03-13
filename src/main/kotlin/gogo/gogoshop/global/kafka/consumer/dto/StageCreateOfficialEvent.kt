package gogo.gogoshop.global.kafka.consumer.dto

data class StageCreateOfficialEvent(
    val id: String,
    val stageId: Long,
    val miniGame: StageCreateOfficialMiniGameSpec,
    val shop: StageCreateOfficialShopDto
)

data class StageCreateOfficialMiniGameSpec(
    val isCoinTossActive: Boolean,
    val isYavarweeActive: Boolean,
    val isPlinkoActive: Boolean
)

data class StageCreateOfficialShopDto(
    val coinToss: StageCreateOfficialCoinTossDto,
    val yavarwee: StageCreateOfficialYavarweeDto,
    val plinko: StageCreateOfficialPlinkoDto,
)

data class StageCreateOfficialPlinkoDto(
    val isActive: Boolean,
    val price: Long?,
    val quantity: Int?,
)

data class StageCreateOfficialYavarweeDto(
    val isActive: Boolean,
    val price: Long?,
    val quantity: Int?,
)

data class StageCreateOfficialCoinTossDto(
    val isActive: Boolean,
    val price: Long?,
    val quantity: Int?,
)


