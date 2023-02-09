package c.e.security.spilcaBook.authority.filter

import org.springframework.beans.factory.annotation.Value
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


//@Component
class StaticKeyAuthenticationFilter() : Filter {


    @Value("\${key}")
    val key: String? = null

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {

        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val keyHeader = httpRequest.getHeader("Key")

        if (!(key.equals(keyHeader))) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return
        }

        filterChain.doFilter(request, response)
    }

}