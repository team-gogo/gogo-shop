package gogo.gogoshop.global.internal.student.api

import gogo.gogoshop.global.feign.client.StudentClient
import gogo.gogoshop.global.internal.student.stub.StudentByIdStub
import org.springframework.stereotype.Component

@Component
class StudentApiImpl(
    private val studentClient: StudentClient
) : StudentApi {
    override fun queryByUserId(userId: Long): StudentByIdStub {
        return studentClient.queryStudentByUserId(userId)
    }
}
