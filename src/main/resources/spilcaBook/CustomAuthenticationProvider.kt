package c.e.security.spilcaBook

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


//@Component
//class CustomAuthenticationProvider
//    : AuthenticationProvider {
//
//
//    @Autowired
//    private val userDetails: UserDetailsService? = null
//
//    @Autowired
//    private val passwordEncoder: PasswordEncoder? = null
//
//    override fun authenticate(authentication: Authentication?): Authentication {
//
//
//        val username = authentication?.name
//        val password = authentication?.credentials.toString()
//
//        val user = userDetails?.loadUserByUsername(username)
//
//        if (user != null && passwordEncoder!!.matches(password, user.password))
//
//            return UsernamePasswordAuthenticationToken(username, password, user.authorities)
//        else
//            throw BadCredentialsException("Bad Credentials: wrong username or password !")
//    }
//
//    override fun supports(authenticationType: Class<*>?): Boolean {
//        return authenticationType == UsernamePasswordAuthenticationToken::class.java
//    }
//
//
//}
//
//


