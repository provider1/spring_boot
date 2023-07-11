package c.e.security.model

import c.e.security.model.RegisterRequest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegisterRequestTest {

    private val username ="ahmed"
    private val email ="gmail"
    private val password ="12345"


    private var registerRequest = RegisterRequest(username , email , password)
    @Test
    @DisplayName("RegisterRequest Details Test")
    fun objectDetailsTest() {

        assertEquals(registerRequest.username , username ,"Should return same names")
        assertEquals(registerRequest.email , email ,"Should return same emails")
        assertEquals(registerRequest.password , password ,"Should return same password")

    }



}