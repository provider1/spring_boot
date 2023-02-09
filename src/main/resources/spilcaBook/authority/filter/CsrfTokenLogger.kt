package c.e.security.spilcaBook.authority.filter

import org.jboss.logging.Logger
import org.springframework.security.web.csrf.CsrfToken
import java.io.IOException
import javax.servlet.*

class CsrfTokenLogger : Filter {
    private val logger = Logger.getLogger(CsrfTokenLogger::class.java.name)

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val o = request.getAttribute("_csrf")
        val token = o as CsrfToken
        logger.info("o $o")
        logger.info("token $token")
        logger.info("CSRF token " + token.token)
        logger.info("token headerName " + token.headerName)
        logger.info("token parameterName " + token.parameterName)
        filterChain.doFilter(request, response)
    }
}