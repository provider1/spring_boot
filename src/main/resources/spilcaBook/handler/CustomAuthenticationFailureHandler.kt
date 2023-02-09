package c.e.security.spilcaBook.handler

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


//@Component
class CustomAuthenticationFailureHandler : AuthenticationFailureHandler {

   override fun onAuthenticationFailure(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse,
        e: AuthenticationException?
    ) {
        httpServletResponse
            .setHeader("failed", LocalDateTime.now().toString())
    }
}