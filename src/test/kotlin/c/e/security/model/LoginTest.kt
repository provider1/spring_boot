package c.e.security.model

import c.e.security.model.Login
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginTest {

    private  val email = "gmail"
    private  val password = "123"

    private var login = Login(email,password)
    @Test
    @DisplayName("login details Test")
    fun loginDetailsTest() {
        assertEquals(login.email , email , "Should have same emails")
        assertEquals(login.password , password , "Should have same passwords")
    }
}