package gogo.gogoshop.global.internal.participant.api

import gogo.gogoshop.global.internal.participant.stub.IsParticipantByStageIdAndStudentIdStub

interface ParticipantApi {
    fun isParticipantByStageIdAndStudentId(stageId: Long, studentId: Long): IsParticipantByStageIdAndStudentIdStub
}