package c.e.security.repository

import c.e.security.entity.Customer
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CustomerRepository : CrudRepository<Customer, Int> {
    fun findCustomerByEmail(email: String): Customer
    fun findByEmail(email: String): Optional<Customer>


    @Query("select c from Customer c where c.email like %:email")
    fun findCustomerWithEmailEndingWith(@Param("email")email:String) : List<Customer>
}

