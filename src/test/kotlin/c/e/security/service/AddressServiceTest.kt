package c.e.security.service

import c.e.security.controller.AddressController
import c.e.security.entity.Address
import org.json.JSONException
import org.json.JSONObject
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressServiceTest {


    @Autowired
    private val testRestTemplate: TestRestTemplate? = null
    private var token: String? = null
    private lateinit var header: HttpHeaders
    private lateinit var request: HttpEntity<String>


    @BeforeAll
    fun setUpUserForTokenAuthenticate() {
        // Arrange
        val loginCredentials = JSONObject()
        loginCredentials.put("email", "test@test.com")
        loginCredentials.put("password", "123")
        header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        header.accept = listOf(MediaType.APPLICATION_JSON)
        request = HttpEntity(loginCredentials.toString(), header)

        val response = testRestTemplate!!.postForEntity("/authenticate",
            request,
            String::class.java)
        token = response.body
        header.setBearerAuth(token!!)
        request = HttpEntity(header)

    }

    @Test
    @DisplayName("Address can be created")
    @Throws(JSONException::class)
    fun addAddressTest() {
        // Act
        val addressJson = JSONObject()
        addressJson.put("customerId", 3)
        addressJson.put("name", "khitan")
        addressJson.put("phoneNumber", "123232133")
        addressJson.put("location", "k")

        request = HttpEntity(addressJson.toString(), header)

        val response2: ResponseEntity<Address> = testRestTemplate!!.postForEntity("/address/add-test",
            request,
            Address::class.java)
        val address = response2.body

        // Assert
        Assertions.assertEquals(
            HttpStatus.OK, response2.statusCode,
            "Response should contain Authorization header with JWT")
        Assertions.assertNotNull(address!!.id, "Address's ID should not be null")

    }




}