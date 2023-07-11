package c.e.security.entityTest

import c.e.security.entity.Customer
import c.e.security.entity.Role
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

//@DataJpaTest –  This is the most important annotation for testing JPA entities in a  Spring Boot application
// it  spawns an in-memory data base and runs our tests  against it. Along with this,
// the  JPA entities are scanned, Transactions, Hibernate  and Spring Data are also configured
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EntityTest {

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Test
    fun entityCustomerValidationTest() {

        val customerEntity =
                Customer("3", "32", "1234", "99", null, false,role = Role.USER, null)

        val storedCustomer = testEntityManager.persistAndFlush(customerEntity)
        println(storedCustomer)


    }


}