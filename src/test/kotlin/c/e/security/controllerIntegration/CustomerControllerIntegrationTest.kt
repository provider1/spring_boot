package c.e.security.controllerIntegration

import c.e.security.entity.Customer
import c.e.security.entity.Role
import org.json.JSONException
import org.json.JSONObject
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersControllerIntegrationTest {

    @Autowired
    private val testRestTemplate: TestRestTemplate? = null
    private var token: String? = null
    @Test
    @DisplayName("User can be created Test")
    @Order(1)
    @Throws(JSONException::class)
    fun testCreateUser_whenValidDetailsProvided_returnsUserDetails() {
        // Arrange

        val userDetailsRequestJson = JSONObject()
        userDetailsRequestJson.put("customerUsername", "Sergey")
        userDetailsRequestJson.put("email", "test@test.com")
        userDetailsRequestJson.put("pass", "123")
        userDetailsRequestJson.put("mobile", "99")
        userDetailsRequestJson.put("role", Role.USER)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val request = HttpEntity(userDetailsRequestJson.toString(), headers)
        // Act
        val createdUserDetailsEntity: ResponseEntity<String> = testRestTemplate!!.postForEntity("/register",
                request,
                String::class.java)
          token  = createdUserDetailsEntity.body

        println(token)
        // Assert
        Assertions.assertEquals(HttpStatus.OK, createdUserDetailsEntity.statusCode)

    }
    @Test
    @DisplayName("/login Test")
    @Order(2)
    @Throws(JSONException::class)
    fun testUserLogin_whenValidCredentialsProvided_returnsJWTinAuthorizationHeader() {
        // Arrange

        val loginCredentials = JSONObject()
        loginCredentials.put("email", "test@test.com")
        loginCredentials.put("password", "123")
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val request = HttpEntity(loginCredentials.toString(),headers)
        println(" request $request" )

        val response= testRestTemplate!!.postForEntity("/authenticate",
                request,
                String::class.java)
        token = response.body
        headers.setBearerAuth(token!!)


        // Act
        val response2: ResponseEntity<Customer> = testRestTemplate.postForEntity("/customers/login",
                request,
                null)


        // Assert
        Assertions.assertNotNull(token,
                "Response should contain Authorization header with JWT")
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode,
                "HTTP Status code should be 200")
    }
}
