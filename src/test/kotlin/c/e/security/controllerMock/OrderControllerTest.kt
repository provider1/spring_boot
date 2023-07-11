package c.e.security.controllerMock

import c.e.security.controller.OrderController
import c.e.security.entity.Address
import c.e.security.entity.Order
import c.e.security.service.OrderService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = [OrderController::class])
class OrderControllerTest {


    lateinit var order: Order
    lateinit var createdOrder: Order

    @MockBean
    lateinit var orderService: OrderService

    @Autowired
    lateinit var mockMvc: MockMvc


    @BeforeAll
    fun setUp() {

        order = Order(
                1,
                ArrayList(),
                Address(),
                "title",
                null,
                "100",
                2,
                "102"
        )


        `when`(orderService.addOrder(any())).thenReturn(order)

        val mockMvcRequestBuilder = MockMvcRequestBuilders.post("/order/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(order))

        val mockMvcResult = mockMvc.perform(mockMvcRequestBuilder).andReturn()
        val mockMvcResponse = mockMvcResult.response.contentAsString

        createdOrder = ObjectMapper().readValue(mockMvcResponse, Order::class.java)

    }


    @Test
    @DisplayName("Order's Customer ID Test")
    fun orderCustomerIdTest() {
        Assertions.assertEquals(order.customerId , createdOrder.customerId , "Customer's IDs Should be the same")
    }

    @Test
    @DisplayName("Order's items Test")
    fun orderItemsTest(){
        Assertions.assertEquals(order.items , createdOrder.items , "Orders Items should be the same ")
    }

    @Test
    @DisplayName("Order's Address Test")
    fun orderAddressTest(){
        Assertions.assertEquals(order.address , createdOrder.address , "Address Should match ")
    }

    @Test
    @DisplayName("Order's title Test")
    fun orderTitleTest(){
        Assertions.assertEquals(order.title , createdOrder.title , "Both address and created address should have same titles")
    }

    @Test
    @DisplayName("Order's Image Test")
    fun orderImageTest(){
        Assertions.assertEquals(order.image , createdOrder.image , "Images have to be the same ")
    }

    @Test
    @DisplayName("Order's SubTotal Test")
    fun orderSubTotalTest(){
        Assertions.assertEquals(order.subTotalAmount , createdOrder.subTotalAmount , "SubTotals Should be equal")
    }

    @Test
    @DisplayName("Order's ShippingCharge Test")
    fun orderShippingChargeTest(){
        Assertions.assertEquals(order.shippingCharge , createdOrder.shippingCharge , "Should be same amount or not null")
    }

    @Test
    @DisplayName("Order's Total Amount Test")
    fun orderTotalAmountTest(){

        Assertions.assertEquals(order.totalAmount , createdOrder.totalAmount , "Should be same")
    }


}