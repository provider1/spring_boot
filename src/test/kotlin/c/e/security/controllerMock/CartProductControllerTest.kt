package c.e.security.controllerMock

import c.e.security.controller.CartProductController
import c.e.security.entity.CartProduct
import c.e.security.service.CartProductService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@WebMvcTest(controllers = [CartProductController::class])
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartProductControllerTest {


    private lateinit var cartProduct: CartProduct
    private lateinit var createdCartProduct: CartProduct

    @MockBean
    lateinit var cartProductService: CartProductService

    @Autowired
    lateinit var mock: MockMvc

    @BeforeAll
    fun setUp() {

        cartProduct = CartProduct(32, 1, "PC", "200", null, "2", "4", 1)
        `when`(cartProductService.addCartProduct(any())).thenReturn(cartProduct)

        val mvcRequestBuilders = MockMvcRequestBuilders
                .post("/cart-product/add-test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(cartProduct))


        val mockMvcResult = mock.perform(mvcRequestBuilders).andReturn()
        val mockMvcResponseString = mockMvcResult.response.contentAsString

        createdCartProduct = ObjectMapper().readValue(mockMvcResponseString, cartProduct::class.java)
    }

    @Test
    @DisplayName("Cart-Product's customerID Test")
    fun cartProductCustomerID() {

        Assertions.assertEquals(cartProduct.customerId, createdCartProduct.customerId, "Customer IDs Should be same but they are not")
    }

    @Test
    @DisplayName("Cart-Product's product ID Test")
    fun cartProductProductIdTest() {

        Assertions.assertEquals(cartProduct.productId, createdCartProduct.productId, "Product IDs should matched")
    }


    @Test
    @DisplayName("Cart-Product's title Test")
    fun cartProductTitleTest() {

        Assertions.assertEquals(cartProduct.title, createdCartProduct.title, "Cart Product's titles have to be the same")
    }


    @Test
    @DisplayName("Cart-Product's Price Test")
    fun cartProductPriceTest() {
        Assertions.assertEquals(cartProduct.price, createdCartProduct.price, "Cart Product's prices should Displayed the same ")

    }

    @Test
    @DisplayName("Cart-Product's Image Test")
    fun cartProductImageTest() {

        Assertions.assertEquals(cartProduct.image, createdCartProduct.image, "Both cart product and created cart product images should be same")
    }


    @Test
    @DisplayName("Cart-Product's Quantity Test")
    fun cartProductCartQuantity() {
        Assertions.assertEquals(cartProduct.cartQuantity, createdCartProduct.cartQuantity, "Cart-Product Cart's Quantity should be matched")
    }


    @Test
    @DisplayName("Cart-Product' Stock Quantity Test")
    fun cartProductStockQuantity() {
        Assertions.assertEquals(cartProduct.stockQuantity, createdCartProduct.stockQuantity, "Cart's Stock Quantity Should be macthed ")
    }


    @Test
    @DisplayName("Cart-Product's ID Test")
    fun cartProductIdTest(){
        Assertions.assertNotNull( createdCartProduct.id , "Cart-Product's ID Shouldn't be null ")
    }


}