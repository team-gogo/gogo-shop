package gogo.gogoshop.global.feign.client

import gogo.gogoshop.global.internal.point.stub.PointStub
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "gogo-stage")
interface PointClient {
    @GetMapping("/stage/api/point/{stage_id}")
    fun queryPointByStageIdAndStudentId(
        @PathVariable("stage_id") stageId: Long,
        @RequestParam("studentId") studentId: Long
    ): PointStub
}