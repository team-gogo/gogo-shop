package gogo.gogoshop.global.internal.student.api

import gogo.gogoshop.global.internal.student.stub.StudentByIdStub

interface StudentApi {
    fun queryByUserId(userId: Long): StudentByIdStub
}