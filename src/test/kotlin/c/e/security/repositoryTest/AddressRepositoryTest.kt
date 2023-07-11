package c.e.security.repositoryTest

import c.e.security.entity.Address
import c.e.security.repository.AddressRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager


@DataJpaTest
class AddressRepositoryTest {
    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Autowired
    private lateinit var addressRepository: AddressRepository


    @Test
    @DisplayName("Get Customer by Email Test")
    fun findAddressByCustomerId() {

        val customerId = 1
        val address = Address(customerId,"k","23213","t","321","go","s","d")
        testEntityManager.persistAndFlush(address)

        val returnedAddress = addressRepository.findAddressBycustomerId(customerId)
        println(returnedAddress)
        Assertions.assertTrue(address.id == address.id, " Both Should have same IDs")

    }









}