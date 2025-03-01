package gogo.gogoshop.global.internal.point.api

import gogo.gogoshop.global.feign.client.PointClient
import org.springframework.stereotype.Component

@Component
class PointApiImpl(
    private val pointClient: PointClient
): PointApi {
    override fun queryPointByStageIdAndStudentId(stageId: Long, studentId: Long) =
        pointClient.queryPointByStageIdAndStudentId(stageId, studentId)
}