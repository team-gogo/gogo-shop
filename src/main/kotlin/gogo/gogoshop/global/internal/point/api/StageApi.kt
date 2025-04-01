package gogo.gogoshop.global.internal.point.api

import gogo.gogoshop.global.internal.point.stub.IsParticipantByStageIdAndStudentIdStub
import gogo.gogoshop.global.internal.point.stub.StageStub

interface StageApi {
    fun queryPointByStageIdAndStudentId(stageId: Long, studentId: Long): StageStub
    fun isParticipantByStageIdAndStudentId(stageId: Long, studentId: Long): IsParticipantByStageIdAndStudentIdStub
}