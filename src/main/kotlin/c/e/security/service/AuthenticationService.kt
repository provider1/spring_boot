package c.e.security.service

import c.e.security.model.AuthenticationRequest
import c.e.security.model.AuthenticationResponse
import c.e.security.entity.Customer
import c.e.security.entity.Role
import c.e.security.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService {

    @Autowired
    private val repository: CustomerRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    private val jwtService: JwtService? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    fun register(customer: Customer): AuthenticationResponse? {
        customer.pass = passwordEncoder!!.encode(customer.pass)
        customer.role = Role.USER
        repository!!.save(customer)
        val jwtToken = jwtService!!.generateToken(customer)
        return AuthenticationResponse(jwtToken)

    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse? {

        authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))


        val customer = repository!!.findByEmail(request.email)
            .orElseThrow { UsernameNotFoundException("User not found") }
        val jwtToken = jwtService!!.generateToken(customer)
        return AuthenticationResponse(jwtToken)
    }


}
