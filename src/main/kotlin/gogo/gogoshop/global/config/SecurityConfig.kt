package gogo.gogoshop.global.config


import gogo.gogoshop.domain.shop.root.application.ShopMapper
import gogo.gogoshop.global.filter.AuthenticationFilter
import gogo.gogoshop.global.filter.LoggingFilter
import gogo.gogoshop.global.handler.CustomAccessDeniedHandler
import gogo.gogoshop.global.handler.CustomAuthenticationEntryPointHandler
import gogo.gogoshop.global.internal.user.stub.Authority
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customAuthenticationEntryPointHandler: CustomAuthenticationEntryPointHandler,
    private val authenticationFilter: AuthenticationFilter,
    private val loggingFilter: LoggingFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity, shopMapper: ShopMapper): SecurityFilterChain {
        http.formLogin { it.disable() }
            .httpBasic { it.disable() }

        http.csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }

        http.exceptionHandling { handling ->
            handling.accessDeniedHandler(customAccessDeniedHandler)
            handling.authenticationEntryPoint(customAuthenticationEntryPointHandler)
        }

        http.sessionManagement { sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(authenticationFilter, LoggingFilter::class.java)

        http.authorizeHttpRequests { httpRequests ->
            httpRequests.requestMatchers(HttpMethod.GET, "/shop/health").permitAll()
            httpRequests.requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()

            // shop
            httpRequests.requestMatchers(HttpMethod.GET, "/shop/{stage_id}").hasAnyRole(Authority.USER.name, Authority.STAFF.name)
            httpRequests.requestMatchers(HttpMethod.POST, "/shop/{shop_id}").hasAnyRole(Authority.USER.name, Authority.STAFF.name)
            httpRequests.requestMatchers(HttpMethod.GET, "/shop/receipt/{shop_id}").hasAnyRole(Authority.USER.name, Authority.STAFF.name)

            httpRequests.anyRequest().denyAll()
        }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        // plz custom allowed client origins
        configuration.allowedOrigins = listOf("*")

        configuration.allowedMethods = listOf(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.OPTIONS.name()
        )

        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}
