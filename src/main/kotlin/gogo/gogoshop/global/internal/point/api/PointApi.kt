package gogo.gogoshop.global.internal.point.api

import gogo.gogoshop.global.internal.point.stub.PointStub

interface PointApi {
    fun queryPointByStageIdAndStudentId(stageId: Long, studentId: Long): PointStub
}