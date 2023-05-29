package c.e.security.controller

import c.e.security.auth.AuthenticationRequest
import c.e.security.auth.AuthenticationService
import c.e.security.auth.RegisterRequest
import c.e.security.model.Customer
import c.e.security.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
class AuthController {

    @Autowired
    private val service: AuthenticationService? = null

    @Autowired
    val jwtService: JwtService? = null

    @PostMapping("/register")
    fun register( @RequestBody request: Customer ): ResponseEntity<String> {
        return ResponseEntity.ok(service!!.register(request)!!.token)
    }

    @PostMapping("/authenticate")
    fun authenticate( @RequestBody request: AuthenticationRequest): ResponseEntity<String> {
        return ResponseEntity.ok(service!!.authenticate(request)!!.token)
    }

    @GetMapping("/demo")
    fun hello(auth : Authentication): ResponseEntity<String> {
        return  ResponseEntity.ok(auth.name)
    }
    
     @PostMapping("/getCustomerId")
    fun getCustomerId(email:String): ResponseEntity<String> {
        return  ResponseEntity.ok(auth.name)
    }


}






