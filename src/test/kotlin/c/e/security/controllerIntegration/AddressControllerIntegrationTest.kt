package c.e.security.controllerIntegration

import c.e.security.entity.Address
import org.json.JSONException
import org.json.JSONObject
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class AddressControllerIntegrationTest {

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
    @Order(1)
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
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode,
                "Response should contain Authorization header with JWT")
        Assertions.assertNotNull(address!!.id, "Address's ID should not be null")

    }

    @Test
    @DisplayName("Delete Address Test")
    @Throws(JSONException::class)
    @Order(5)
    fun deleteAddressTest() {

        // Act
        val addressId = 1

        val response2 =
                testRestTemplate!!.exchange("/address/delete/address-id/$addressId", HttpMethod.DELETE, request, Void::class.java)


        // Assert
        println(response2.body)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode,
                "")
    }


    @Test
    @DisplayName("Delete All Addresses By Customer's Id Test")
    @Throws(JSONException::class)
    @Order(4)

    fun deleteAllAddressesByCustomerIdTest() {

        // Act
        val customerId = 3
        val response2 =
                testRestTemplate!!.exchange("/address/delete-all/customer-id/$customerId", HttpMethod.DELETE, request, Void::class.java)

        // Assert
        println(response2.body)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode,
                "")
    }


    @Test
    @Order(3)
    @DisplayName("Get addresses by customer's ID Test")
    fun getAddressesByCustomerIdTest() {
        // Act

        val customerId = 6
        val response: ResponseEntity<List<Address>> = testRestTemplate!!.exchange<List<Address>>("/address/get/$customerId",
                HttpMethod.GET,
                request,
                object : ParameterizedTypeReference<List<Address?>?>() {})
        val address = response.body
        // Assert
        println(address)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode,"")

    }




    @Test
    @DisplayName("Get all addresses Test")
    @Order(2)
    fun getAllAddresses(){
        // Act
        val response = testRestTemplate!!.exchange("/address/get-all",
                HttpMethod.GET,
                request,
                object : ParameterizedTypeReference<List<Address>>() {})


        println(response.body)
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.statusCode,
                "HTTP Status code should be 200")
        Assertions.assertTrue(response.body!!.size > 1,
                "There should be exactly 1 user in the list")
    }


}