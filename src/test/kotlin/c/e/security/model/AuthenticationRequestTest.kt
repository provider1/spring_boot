package c.e.security.model

import c.e.security.model.AuthenticationRequest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationRequestTest {


    private lateinit var authenticationRequest: AuthenticationRequest

    @Test
    @BeforeAll
    fun setUp(){
        authenticationRequest = AuthenticationRequest("gmail" ,"123")
    }
    @Test
    @DisplayName("Test AuthenticationRequest Model Test")
    fun checkInfo() {
        assertTrue(authenticationRequest is AuthenticationRequest)
        assertEquals(authenticationRequest.email , "gmail")
        assertEquals(authenticationRequest.password , "123")
    }


}

