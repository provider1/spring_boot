package c.e.security.repository

import c.e.security.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Int> {


    fun findOrdersBycustomerId(customerId:Int):ArrayList<Order>

    @Modifying
    @Query(value =  "delete from orders   where customer_id = ?1" , nativeQuery = true)
    fun deleteAllOrdersBycustomerId(customerId: Int)
}