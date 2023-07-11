package c.e.security.util

import c.e.security.entity.Customer
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.password.PasswordEncoder

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@SpringBootTest
class MyUtilTest {


    private val myUtil = MyUtil()
    private var customer = Customer("ahmed", "gmail", "23")
    @Autowired
    private val passwordEncoder: PasswordEncoder? = null
    @Test
    @DisplayName("Logging Test")
    fun info() {
        myUtil.info("logging works fine")

    }

    @Test
    @DisplayName("field not null Test")
    fun customerDetailsAreNotNull() {
        var returnedBoolean = myUtil.customerDetailsAreNotNull(customer)
        assertTrue(returnedBoolean, "One of the fields is null")

        customer = Customer("")
        returnedBoolean = myUtil.customerDetailsAreNotNull(customer)
        assertFalse(returnedBoolean, "One of the fields is null")


    }

    @Test
    @DisplayName("Response Failed message Test")
    fun getResponseFailedMessage() {
        customer = Customer("", "gmail", "")
        var returnedString = myUtil.getResponseFailedMessage(customer)
        assertNotEquals(returnedString, "", "Should return empty message")

        customer = Customer("ahmed", "", "")
        returnedString = myUtil.getResponseFailedMessage(customer)
        assertNotEquals(returnedString, "", "Should return empty message")

        customer = Customer("", "", "12345")
        returnedString = myUtil.getResponseFailedMessage(customer)
        assertNotEquals(returnedString, "", "Should return empty message")
    }
    @Test
    @DisplayName("Crypt password Test")
    fun crypt() {
        var password = "123"
        var hashedPassword =  myUtil.crypt(password)
        assertNotNull(hashedPassword )

    }

    @Test
    @DisplayName("Password Matching Test")
    fun comparePassword() {
        var password = "123"
        var hashedPassword =  myUtil.crypt(password)

        val hashIsMatchPassword = myUtil.comparePassword(password , hashedPassword)
        assertTrue(hashIsMatchPassword)

    }
}