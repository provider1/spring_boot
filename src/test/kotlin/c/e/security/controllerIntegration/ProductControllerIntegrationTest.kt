package c.e.security.controllerIntegration

import c.e.security.entity.Product
import org.json.JSONObject
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerIntegrationTest {

    @Autowired
    private val testRestTemplate: TestRestTemplate? = null
    private var token: String? = null
    private lateinit var header: HttpHeaders
    private lateinit var request: HttpEntity<String>
    private var customerId: Int = 10


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
    @Disabled("Add Order Test")
    fun addProductTest() {
        // Act

        val productJSON = JSONObject()
        productJSON.put("ownerId", customerId)
        productJSON.put("ownerUsername", "ahmed")
        productJSON.put("title", "infinite number")
        productJSON.put("price", 10.9)
        productJSON.put("description", "Good Product")
        productJSON.put("quantity", 2)
        productJSON.put("image", null)

        request = HttpEntity(productJSON.toString(), header)

        val response2: ResponseEntity<Product> = testRestTemplate!!.postForEntity("/products/add",
                request,
                Product::class.java)
        val orderObject = response2.body

        println(orderObject)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode, "")


    }


    @Test
    @Disabled("Get Products Test")
    fun getProductsTest() {
        // Act

        val response2 = testRestTemplate!!.exchange("/products/get-all", HttpMethod.GET, request, object : ParameterizedTypeReference<List<Product>>() {})
        val products = response2.body

        println(products)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode, "")


    }
    @Test
    @Disabled("Get Product By Owner ID Test")
    fun getProductByOwnerIdTest() {
        // Act

        val ownerId = 10
        val response2 = testRestTemplate!!.exchange("/products/get/$ownerId", HttpMethod.GET, request, object : ParameterizedTypeReference<List<Product>>() {})
        val products = response2.body

        println(products)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode, "")


    }


      @Test
    @Disabled("Delete Products By Owner ID Test")
    fun deleteProductsByOwnerIdTest() {
        // Act

        val ownerId = 10
        val response2 = testRestTemplate!!.exchange("/products/delete-all/owner-id/$ownerId", HttpMethod.DELETE, request, String::class.java)
        val deleteMessage = response2.body

        println(deleteMessage)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode, "")


    }

    @Test
    @Disabled("Delete Product by ID Test")
    fun deleteProductsByIdTest() {
        // Act

        val productId = 2
        val response2 = testRestTemplate!!.exchange("/products/delete/product-id/$productId", HttpMethod.DELETE, request, String::class.java)
        val deleteMessage = response2.body

        println(deleteMessage)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode, "")


    }
    @Test
    @DisplayName("Update Quantity Product by ID Test")
    fun updateProductQuantityByIdTest() {
        // Act

        val productId = 3
        val productQuantity = 1
        val response2 = testRestTemplate!!.exchange("/products/update/product-id/$productId/$productQuantity", HttpMethod.POST, request, Void::class.java)

        Assertions.assertEquals(HttpStatus.OK, response2.statusCode, "")


    }



}