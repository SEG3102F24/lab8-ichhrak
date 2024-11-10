package seg3102.lab8_employee_api_graphql.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors { cors -> cors.configure(http) }
        http.csrf { csrf -> csrf.disable() }
        http.authorizeHttpRequests { auth -> auth.anyRequest().permitAll() }
        return http.build()
    }
}