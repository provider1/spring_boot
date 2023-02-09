package c.e.security.auth

import c.e.security.model.Customer
import c.e.security.model.Role
import c.e.security.repository.CustomerRepository
import c.e.security.service.JwtService
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

    fun register(request: Customer): AuthenticationResponse? {
        val customer = Customer()
        customer.email = request.email.toString()
        customer.customerUsername = request.username
        customer.pass = passwordEncoder!!.encode(request.pass)
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
