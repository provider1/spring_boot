package c.e.security.repositoryTest

import c.e.security.entity.Customer
import c.e.security.entity.Role
import c.e.security.repository.CustomerRepository
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager


//@DataJpaTest provides some standard setup needed for testing the persistence layer:
//configuring H2, an in-memory database
//setting Hibernate, Spring Data, Transactions, and the DataSource
//performing an @EntityScan
//turning on SQL logging
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Test
    @Disabled("Get Customer by Email Test")
    fun getCustomerByEmail() {
        val customerEmail = "w"
        val customerEntity = Customer("3", customerEmail, "1234", "99", null, false, role = Role.USER, null)

        testEntityManager.persistAndFlush(customerEntity)

        val returnedCustomer = customerRepository.findCustomerByEmail(customerEmail)
        println(returnedCustomer)
        Assertions.assertTrue(customerEntity.id == returnedCustomer.id, " Both Should have same IDs")

    }


    @Test
    @Disabled("Get Customer By Id Test")
    fun getCustomerById() {
        val customerEmail = "w"
        val customerEntity = Customer("3", customerEmail, "1234", "99", null, false, role = Role.USER, null)

        testEntityManager.persistAndFlush(customerEntity)

        println(customerEntity)
        val returnedCustomer = customerRepository.findById(1).get()
        println(returnedCustomer)
        Assertions.assertTrue(customerEntity.id == returnedCustomer.id, " Both Should have same IDs")

    }


    @Test
    @Disabled("Get Customers By Email Ends With Test")
    fun getCustomersByEmailEndsWith() {
        val selectedEmail = ".com"
        val customerEntity = Customer("3", "s.com", "1234", "99", null, false, role = Role.USER, null)
        val customerEntity2 = Customer("3", "e.com", "1234", "99", null, false, role = Role.USER, null)

        testEntityManager.persistAndFlush(customerEntity)
        testEntityManager.persistAndFlush(customerEntity2)

        println(customerEntity)
        println(customerEntity2)

        val customers = customerRepository.findCustomerWithEmailEndingWith(selectedEmail)
        println("----------------")
        println(customers)
        Assertions.assertTrue(customers.isNotEmpty(),"Should at least contains one record")

    }


}