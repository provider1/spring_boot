package c.e.security.spilcaBook.oauth2Customized.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager

////@Configuration
//class WebSecurityConfig : WebSecurityConfigurerAdapter() {
//
//
//    @Bean
//    fun userDetailsManager(): UserDetailsService {
//        val userManager = InMemoryUserDetailsManager()
//        val user = User
//            .withUsername("ahmed")
//            .password("12345")
//            .authorities("read")
//            .build()
//        userManager.createUser(user)
//        return InMemoryUserDetailsManager()
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//         return NoOpPasswordEncoder.getInstance()!!
//    }
//
//    @Bean
//    fun authenticationManagerBeans(): AuthenticationManager {
//        return super.authenticationManagerBean()
//    }
//
//
//}