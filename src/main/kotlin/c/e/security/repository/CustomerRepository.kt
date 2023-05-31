package c.e.security.repository

import c.e.security.model.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CustomerRepository : CrudRepository<Customer, Int> {
    fun findCustomerByEmail(email:String):Customer
      fun findByEmail(email: String): Optional<Customer>




}

