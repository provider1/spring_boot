package c.e.security.spilcaBook.authority.filter

import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestValidationFilter : Filter {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse
        val requestId = httpRequest.getHeader("Request-Id")
        if (requestId == null || requestId.isBlank()) {
            httpResponse.status = HttpServletResponse.SC_BAD_REQUEST
            return
        }
        filterChain.doFilter(request, response)
    }
}
