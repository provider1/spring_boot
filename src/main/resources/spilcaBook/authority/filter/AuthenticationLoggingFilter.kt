package c.e.security.spilcaBook.authority.filter

import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.logging.Logger
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthenticationLoggingFilter : Filter {
    private val logger = Logger.getLogger("Logging Filter")

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val cookie = httpRequest.getHeader("Cookie")
        val postmanToken = httpRequest.getHeader("Postman-Token")
        val host = httpRequest.getHeader("Host")
        val userAgent = httpRequest.getHeader("User-Agent")

        val accept = httpRequest.getHeader("Accept")
        val acceptEncoding = httpRequest.getHeader("Accept-Encoding")
        val connection = httpRequest.getHeader("Connection")
        val requestId = httpRequest.getHeader("Request-Id")
        val secretKey = httpRequest.getHeader("Secret-Key")
        val image = httpRequest.getPart("image")


        logger.info("--------------------------------------------------------------------------------")
        logger.info("Successfully authenticated request with cookie $cookie")
        logger.info("Successfully authenticated request with postman-Token $postmanToken")
        logger.info("Successfully authenticated request with host $host")
        logger.info("Successfully authenticated request with user-Agent $userAgent")

        logger.info("Successfully authenticated request with accept $accept")
        logger.info("Successfully authenticated request with accept-Encoding $acceptEncoding")
        logger.info("Successfully authenticated request with connection $connection")
        logger.info("Successfully authenticated request with id $requestId")
        logger.info("Successfully authenticated request with Secret-Key $secretKey")
        logger.info("Successfully authenticated request with image $image")
        logger.info("--------------------------------------------------------------------------------")


        filterChain.doFilter(request, response)
    }
}


//doFilterInternal function is Same contract as for doFilter,
// but guaranteed to be just invoked once
// per request within a single request thread.
class AuthenticationLoggingFilterOncePerRequestFilter : OncePerRequestFilter() {
    private val logger = Logger.getLogger("Logging Filter")

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(httpRequest: HttpServletRequest, httpResponse: HttpServletResponse, filterChain: FilterChain) {
        val cookie = httpRequest.getHeader("Cookie")
        val postmanToken = httpRequest.getHeader("Postman-Token")
        val host = httpRequest.getHeader("Host")
        val userAgent = httpRequest.getHeader("User-Agent")

        val accept = httpRequest.getHeader("Accept")
        val acceptEncoding = httpRequest.getHeader("Accept-Encoding")
        val connection = httpRequest.getHeader("Connection")


        logger.info("--------------------------------------------------------------------------------")
        logger.info("Successfully authenticated request with cookie $cookie")
        logger.info("Successfully authenticated request with postman-Token $postmanToken")
        logger.info("Successfully authenticated request with host $host")
        logger.info("Successfully authenticated request with user-Agent $userAgent")

        logger.info("Successfully authenticated request with accept $accept")
        logger.info("Successfully authenticated request with accept-Encoding $acceptEncoding")
        logger.info("Successfully authenticated request with connection $connection")
        logger.info("--------------------------------------------------------------------------------")


        filterChain.doFilter(httpRequest, httpResponse)
    }
}
