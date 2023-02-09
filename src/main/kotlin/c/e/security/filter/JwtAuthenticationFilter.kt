package c.e.security.filter

import c.e.security.service.JwtService


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Suppress(
    "UNREACHABLE_CODE",
    "ALWAYS_NULL",
    "SENSELESS_COMPARISON",
    "unused",
    "UNUSED_VARIABLE",
    "ControlFlowWithEmptyBody"
)
@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    val logger: Logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    private var jwtService: JwtService = JwtService()
    @Autowired
    val userDetailsService: UserDetailsService? = null

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null ) {
            filterChain.doFilter(request, response);
            return;
        }

        val jwt = authHeader
        val userEmail = jwtService.extractEmail(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().authentication  == null) {
            val userDetails = userDetailsService!!.loadUserByUsername(userEmail)

            if (jwtService.isTokenValid(jwt, userDetails)) {
                val authenticationToken = UsernamePasswordAuthenticationToken(userDetails , null, userDetails.authorities)
                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}


