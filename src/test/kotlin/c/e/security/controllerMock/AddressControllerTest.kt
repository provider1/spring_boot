package c.e.security.controllerMock

import c.e.security.controller.AddressController
import c.e.security.entity.Address
import c.e.security.service.AddressService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.mockito.Mockito.`when`
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

// Spring Boot instantiates only the web layer rather than the whole context.
// In an application with multiple controllers,
// you can even ask for only one to be instantiated by using,
// for example, @WebMvcTest(HomeController.class).
@WebMvcTest(controllers = [AddressController::class])
@AutoConfigureMockMvc(addFilters = false)
class AddressControllerTest {


    private lateinit var address: Address
    private lateinit var createdAddress: Address


    @MockBean
    lateinit var addressService: AddressService
    @Autowired
    lateinit var mvcMock: MockMvc
    @BeforeAll
    fun setUp() {

        address = Address(
                32,
                "khitan",
                "999",
                "kuwait",
                "0032",
                "knock the door",
                "office",
                "no other address",
                1)

        `when`(addressService.addAddress(any())).thenReturn(address)

        val addressAsString =ObjectMapper().writeValueAsString(address)
        val mvcRequestBuilder =
                MockMvcRequestBuilders.post("/address/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(addressAsString)

        println(addressAsString)

        val mvcResult = mvcMock.perform(mvcRequestBuilder).andReturn()
        val mvcResponseString = mvcResult.response.contentAsString
        println("MVC Result : $mvcResult")
        println("MVC Response : $mvcResponseString")
        createdAddress = ObjectMapper().readValue(mvcResponseString, Address::class.java)

    }

    @Test
    @DisplayName("Address's ID Test")
    fun addressIdTest() {
        Assertions.assertNotNull(address.id, "Address ID should never be null")
    }

    @Test
    @DisplayName("Address's name Test")
    fun addressNameTest() {
        Assertions.assertEquals(address.name, createdAddress.name, "Address's name should be same as the created address's name")
    }

    @Test
    @DisplayName("Address's Phone Number Test")
    fun addressPhoneNumberTest() {
        Assertions.assertEquals(address.phoneNumber, createdAddress.phoneNumber, "Address's phone number should be same as the created address's phone number")
    }

    @Test
    @DisplayName("Address's Location Test")
    fun addressLocationTest(){
        Assertions.assertEquals(address.location , createdAddress.location , "Locations Should be Matched")
    }

    @Test
    @DisplayName("address's Zip Code Test")
    fun addressZipCodeTest(){
        Assertions.assertEquals(address.zipCode , createdAddress.zipCode , "Both Addresses should have the same zip code")
    }


    @Test
    @DisplayName("Address Customer's ID Test")
    fun addressCustomerIdTest(){
        Assertions.assertEquals(address.customerId , createdAddress.customerId , "IDs Should be same")

    }

    @Test
    @DisplayName("Address's Type Test")
    fun addressTypeTest(){
        Assertions.assertEquals(address.addressType , createdAddress.addressType , "Address's Type Should be the same")
    }


    @Test
    @DisplayName("Address's other address Test")
    fun otherAddressTest(){
        Assertions.assertEquals(address.otherAddress , createdAddress.otherAddress, "Address's other address Should be the same")
    }

}