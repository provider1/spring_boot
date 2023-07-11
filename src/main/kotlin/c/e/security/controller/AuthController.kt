package c.e.security.controller

import c.e.security.model.AuthenticationRequest
import c.e.security.service.AuthenticationService
import c.e.security.entity.Customer
import c.e.security.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
class AuthController {

    @Autowired
    private val service: AuthenticationService? = null



    @PostMapping("/register")
    fun register( @RequestBody customer: Customer ): ResponseEntity<String> {
        return ResponseEntity.ok(service!!.register(customer)!!.token)
    }

    @PostMapping("/authenticate")
    fun authenticate( @RequestBody request: AuthenticationRequest): ResponseEntity<String> {
        return ResponseEntity.ok(service!!.authenticate(request)!!.token)
    }
}






