package c.e.security.controllerMock

import c.e.security.controller.ProductController
import c.e.security.entity.Product
import c.e.security.service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*

import org.mockito.Mockito.`when`
import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = [ProductController::class])
class ProductControllerTest {


    private lateinit var product: Product
    private lateinit var createdProduct: Product


    @MockBean
    lateinit var productService: ProductService

    @Autowired
    lateinit var mockMvc: MockMvc


    @BeforeAll
    fun init() {
        product = Product(1, "ahmed", "pc", 720.4, "Good PC", 10, null, 34)


        `when`(productService.addProduct(any())).thenReturn(product)

        val mockMvcRequestBuilder = MockMvcRequestBuilders
                .post("/products/add-test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(product))

        val mvcResult = mockMvc.perform(mockMvcRequestBuilder).andReturn()
        val mockMvcResponse = mvcResult.response.contentAsString

        createdProduct = ObjectMapper().readValue(mockMvcResponse, Product::class.java)

    }

    @Test
    @DisplayName("Product's Owner ID Test")
    fun productOwnerIdTest() {
        Assertions.assertEquals(product.ownerId , createdProduct.ownerId , " Should return same Owner IDs")


    }


    @Test
    @DisplayName("Product Owner Username Test")
    fun productOwnerUsernameTest(){
        Assertions.assertEquals(product.ownerUsername , createdProduct.ownerUsername , "Should return same owner name")
    }


    @Test
    @DisplayName("Product's Title Test")
    fun productTitleTest(){
        Assertions.assertEquals(product.title , createdProduct.title , "Titles Should be matched")
    }

    @Test
    @DisplayName("Product's Price Test")
    fun productPriceTest(){

        Assertions.assertEquals(product.price , createdProduct.price , "Prices Should be the same")
    }

    @Test
    @DisplayName("Product's Description Test")
    fun productDescriptionTest(){
        Assertions.assertEquals(product.description, createdProduct.description , "Description Should be same")
    }

    @Test
    @DisplayName("Product's Quantity Test")
    fun productQuantityTest(){
        Assertions.assertEquals(product.quantity , createdProduct.quantity , "Quantity Should be the same")
    }

    @Test
    @DisplayName("Product's Image Test")
    fun productImageTest(){
        Assertions.assertEquals(product.image , createdProduct.image , "Images Should match")
    }


    @Test
    @DisplayName("Product's ID Test")
    fun productIdTest(){
        Assertions.assertNotNull( createdProduct.id, "ID Should not be null")
    }
    // owner ID , owner Username , title  , price , description , quantity , image , id


}