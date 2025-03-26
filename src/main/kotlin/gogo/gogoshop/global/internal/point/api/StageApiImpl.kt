package gogo.gogoshop.global.internal.point.api

import gogo.gogoshop.global.feign.client.StageClient
import gogo.gogoshop.global.internal.point.stub.IsParticipantByStageIdAndStudentIdStub
import org.springframework.stereotype.Component

@Component
class StageApiImpl(
    private val stageClient: StageClient
): StageApi {
    override fun queryPointByStageIdAndStudentId(stageId: Long, studentId: Long) =
        stageClient.queryPointByStageIdAndStudentId(stageId, studentId)

    override fun isParticipantByStageIdAndStudentId(
        stageId: Long,
        studentId: Long
    ): IsParticipantByStageIdAndStudentIdStub =
        stageClient.isParticipantByStageIdAndStudentId(stageId, studentId)
}