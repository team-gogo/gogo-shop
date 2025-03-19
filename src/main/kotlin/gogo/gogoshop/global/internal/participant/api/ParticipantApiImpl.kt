package gogo.gogoshop.global.internal.participant.api

import gogo.gogoshop.global.feign.client.ParticipantClient
import gogo.gogoshop.global.internal.participant.stub.IsParticipantByStageIdAndStudentIdStub
import org.springframework.stereotype.Component

@Component
class ParticipantApiImpl(
    private val participantClient: ParticipantClient
): ParticipantApi {
    override fun isParticipantByStageIdAndStudentId(
        stageId: Long,
        studentId: Long
    ): IsParticipantByStageIdAndStudentIdStub {
        return participantClient.isParticipantByStageIdAndStudentId(stageId, studentId)
    }
}