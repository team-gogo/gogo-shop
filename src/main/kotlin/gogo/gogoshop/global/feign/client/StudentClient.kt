package gogo.gogoshop.global.feign.client

import gogo.gogoshop.global.internal.student.stub.StudentByIdStub
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "gogo-user")
interface StudentClient {
    @GetMapping("/user/student")
    fun queryStudentByUserId(
        @RequestParam("userId") userId: Long
    ): StudentByIdStub
}
