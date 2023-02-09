package c.e.security.repository

import c.e.security.model.Address
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

//CrudRepository is Spring Data interface for generic CRUD operations on a repository of a specific type
@Repository
interface AddressRepository : CrudRepository<Address, Int> {


    fun findAddressBycustomerId(customerId:Int):ArrayList<Address>

    @Modifying
    @Query(value =  "delete from address   where customer_id = ?1" , nativeQuery = true)
    fun deleteAllAddressesBycustomerId(customerId: Int)



//
//    @Query("select c from Customer c where c.cUsername = ?1 ") // JPQL
//    fun getCustomersByName(name: String): List<Customer>
//
//    @Query("select c.cUsername from Customer c where c.cUid = ?1 ") // JPQL
//    fun getNameByid(id: Int): String
//
//    @Modifying
//    @Query("select c.cUsername from Customer c where c.cMobile = :mobile") // JPQL
//    fun getNameByMobile(@Param("mobile") mobile: String, pageable: PageRequest): List<String>
}
