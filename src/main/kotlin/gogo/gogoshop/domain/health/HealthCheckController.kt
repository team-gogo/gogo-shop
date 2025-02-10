package gogo.gogoshop.domain.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/shop/health")
    fun healthCheck() = "GOGO Shop Service OK"

}
