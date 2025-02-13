package gogo.gogoshop.global.feign

import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import gogo.gogoshop.global.error.ShopException
import org.springframework.http.HttpStatus

class FeignClientErrorDecoder : ErrorDecoder {
    override fun decode(
        methodKey: String?,
        response: Response,
    ): Exception? {
        if (response.status() >= 400) {
            throw ShopException("HTTP 통신 오류", HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
        return FeignException.errorStatus(methodKey, response)
    }
}
