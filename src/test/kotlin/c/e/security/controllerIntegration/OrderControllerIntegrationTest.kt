package c.e.security.controllerIntegration

import c.e.security.entity.Order
import org.json.JSONObject
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderControllerIntegrationTest {

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
    fun addOrderTest() {
        // Act
        val addressJson = JSONObject()
        addressJson.put("customerId", 98)
        addressJson.put("name", "khitan")
        addressJson.put("phoneNumber", "123232133")
        addressJson.put("location", "k")

        val orderJSON = JSONObject()
        orderJSON.put("customerId", customerId)
        orderJSON.put("items", null)
        orderJSON.put("address", addressJson)
        orderJSON.put("title", "infinite number")
        orderJSON.put("image", null)
        orderJSON.put("subTotalAmount", "4")
        orderJSON.put("shippingCharge", "3")
        orderJSON.put("date", null)
        orderJSON.put("id", 4)

        request = HttpEntity(orderJSON.toString(), header)

        val response2: ResponseEntity<Order> = testRestTemplate!!.postForEntity("/order/add",
                request,
                Order::class.java)
        val orderObject = response2.body

        println(orderObject)
        Assertions.assertEquals(HttpStatus.OK, response2.statusCode, "")


    }

    @Test
    @Disabled("Get All Orders Test")
    fun getAllOrdersTest() {

        request = HttpEntity(header)
        val response =
                testRestTemplate!!.exchange("/order/get-all", HttpMethod.GET, request, object : ParameterizedTypeReference<List<Order>>() {})


        val ordersList = response.body

        println(ordersList)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode, "")


    }


    @Test
    @Disabled("Get All Orders by Customer's ID Test")
    fun getAllOrdersByCustomerIdTest() {

        val customerId = 10
        request = HttpEntity(header)
        val response =
                testRestTemplate!!.exchange("/order/get-by-customer-id/$customerId", HttpMethod.GET, request, object : ParameterizedTypeReference<List<Order>>() {})


        val ordersList = response.body

        println(ordersList)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode, "")


    }

    @Test
    @Disabled("Delete Order By Id Test")
    fun deleteOrderByIdTest() {

        val orderId = 3
        request = HttpEntity(header)
        val response =
                testRestTemplate!!.exchange("/order/delete/$orderId", HttpMethod.DELETE, request, String::class.java)


        val deleteMessage = response.body

        println(deleteMessage)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode, "")


    }

    @Test
    @DisplayName("Delete  All Orders By Customer Id Test")
    fun deleteAllOrdersByCustomerIdTest() {

        val customerId = 10
        request = HttpEntity(header)
        val response =
                testRestTemplate!!.exchange("/order/delete-all/customer-id/$customerId", HttpMethod.DELETE, request, Void::class.java)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode, "")


    }



}