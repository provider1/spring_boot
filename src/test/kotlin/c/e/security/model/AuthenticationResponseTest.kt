package c.e.security.model

import c.e.security.model.AuthenticationResponse
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationResponseTest {

    private var token = "12323453"

    private var authenticationResponse = AuthenticationResponse(token)

    @Test
    @DisplayName("Token Test")
    fun getToken() {


        assertEquals(authenticationResponse.token, token, "Should be same token")

    }


}