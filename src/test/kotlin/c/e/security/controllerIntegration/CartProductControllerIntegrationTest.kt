package c.e.security.controllerIntegration

import c.e.security.entity.CartProduct
import org.json.JSONException
import org.json.JSONObject
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartProductControllerIntegrationTest {

    @Autowired
    private val testRestTemplate: TestRestTemplate? = null
    private var token: String? = null
    private lateinit var header: HttpHeaders
    private lateinit var request: HttpEntity<String>
    private var customerId: Int = 1

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
    @DisplayName("Cart-Product can be created")
    @Throws(JSONException::class)
    fun addCartProductTest() {
        // Act
        val cartProductJSON = JSONObject()
        cartProductJSON.put("customerId", customerId)
        cartProductJSON.put("productId", 323)
        cartProductJSON.put("title", "PC")
        cartProductJSON.put("price", "10")
        cartProductJSON.put("image", null)
        cartProductJSON.put("cartQuantity", "10")

        request = HttpEntity(cartProductJSON.toString(), header)

        val response2: ResponseEntity<CartProduct> = testRestTemplate!!.postForEntity("/cart-product/add-test",
                request,
                CartProduct::class.java)
        val cartProductObject = response2.body

        println(cartProductObject)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode, "")

    }

    @Test
    @Disabled("Delete-Cart Product by Cart=Product's ID Test")
    @Throws(JSONException::class)
    fun deleteCartProductByCartProductIdTest() {
        // Act
        val productId = 2
        val response2 =
                testRestTemplate!!.exchange("/cart-product/delete/cart-product-id/$productId", HttpMethod.DELETE, request, Void::class.java)
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode,
                "")
    }


    @Test
    @Disabled("Delete-Cart Product by Customer's ID ID Test")
    @Throws(JSONException::class)
    fun deleteCartProductTest() {
        // Act
        val customerId = 1
        val response2 =
                testRestTemplate!!.exchange("/cart-product/delete-all/customer-id/$customerId", HttpMethod.DELETE, request, Void::class.java)
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode,
                "")
    }

    @Test
    @DisplayName("Get All Test")
    @Throws(JSONException::class)
    fun getAllCartProductsTest() {
        // Act
        val response =
                testRestTemplate!!.exchange("/cart-product/get-all", HttpMethod.GET, request, object : ParameterizedTypeReference<List<CartProduct>>() {})

        println(response.body)
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.statusCode,
                "")

        Assertions.assertNotNull(response.body!!, "Should return at least one record")


    }


    @Test
    @DisplayName("Get Cart-Products by Customer's ID Test")
    @Throws(JSONException::class)
    fun getCartProductsByCustomerIdTest() {
        // Act
        val response =
                testRestTemplate!!.exchange("/cart-product/get-by-customerId/$customerId", HttpMethod.GET, request, object : ParameterizedTypeReference<List<CartProduct>>() {})

        println(response.body)
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.statusCode,
                "")

        Assertions.assertNotNull(response.body!!, "Should return at least one record")


    }
    @Test
    @Disabled("Get Cart-Products by Product's ID Test")
    @Throws(JSONException::class)
    fun getCartProductsByProductIdTest() {
        // Act
        val productId =323
        val response =
                testRestTemplate!!.exchange("/cart-product/get-by-product-id/$productId", HttpMethod.GET, request, object : ParameterizedTypeReference<List<CartProduct>>() {})

        println(response.body)
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.statusCode,
                "")

        Assertions.assertTrue(response.body!!.isNotEmpty(), "Should return at least one record")


    }


}