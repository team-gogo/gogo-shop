package gogo.gogoshop.global.feign.client

import gogo.gogoshop.global.internal.participant.stub.IsParticipantByStageIdAndStudentIdStub
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "gogo-stage")
interface ParticipantClient {
    @GetMapping("/stage/api/participant/{stage_id}")
    fun isParticipantByStageIdAndStudentId(
        @PathVariable("stage_id") stageId: Long,
        @RequestParam("studentId") studentId: Long
    ): IsParticipantByStageIdAndStudentIdStub
}