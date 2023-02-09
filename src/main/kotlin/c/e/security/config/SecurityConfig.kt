package c.e.security.config

import c.e.security.filter.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfig  {
//    override fun configure(http: HttpSecurity?) {
//        http!!.csrf().disable()
//            .authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/register").permitAll()
//            .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
//
//    }

    @Autowired
    private val jwtAuthenticationFilter: JwtAuthenticationFilter? = null

    @Autowired
    private val authenticationProvider: AuthenticationProvider? = null

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {


        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/register").permitAll()
            .antMatchers("/customers/register").permitAll()
            .antMatchers("/authenticate").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)


        return http.build()
    }


}