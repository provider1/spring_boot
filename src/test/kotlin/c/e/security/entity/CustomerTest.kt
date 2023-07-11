package c.e.security.entity

import net.bytebuddy.utility.dispatcher.JavaDispatcher.Instance
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerTest {

    private val username = "ahmed"
    private val email = "gmail"
    private val password = "123"

    var customer = Customer(username,email ,password,"321",null ,true,Role.USER,"2233",1)

    @Test
    @DisplayName("Get Customer's Authorities Test")
    fun getAuthorities() {

        assertNotNull(customer.authorities)
    }

    @Test
    @DisplayName("Get Customer's password Test")
    fun getPassword() {
        assertEquals(customer.password , password)
    }

    @Test
    @DisplayName("Get Customer's username Test")
    fun getUsername() {
        //customer's username is his email instead of his username
        // because emails are unique in our system and usernames are not !
        assertEquals(customer.username , email)
    }

    @Test
    @DisplayName("Check if customer's account is not Expired Test")
    fun isAccountNonExpired() {
        assertTrue(customer.isAccountNonExpired)
    }

    @Test
    @DisplayName("Check if customer's account is not Locked Test")
    fun isAccountNonLocked() {
        assertTrue(customer.isAccountNonLocked)
    }

    @Test
    @DisplayName("Check if customer's credentials are not Expired Test")
    fun isCredentialsNonExpired() {
        assertTrue(customer.isCredentialsNonExpired)
    }

    @Test
    @DisplayName("Check if customer's account is Enabled Test")
    fun isEnabled() {
        assertTrue(customer.isEnabled)
    }
}