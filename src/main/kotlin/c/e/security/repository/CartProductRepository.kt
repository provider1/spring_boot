package c.e.security.repository

import c.e.security.model.CartProduct
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CartProductRepository : CrudRepository<CartProduct, Int> {


    fun findCartProductBycustomerId(customerId:Int):ArrayList<CartProduct>
    fun findCartProductByproductId(productId: Int): ArrayList<CartProduct>



    @Modifying
    @Query(value =  "delete from cart_product   where customer_id = ?1" , nativeQuery = true)
    fun deleteAllCartProductsBycustomerId(customerId:Int)



}