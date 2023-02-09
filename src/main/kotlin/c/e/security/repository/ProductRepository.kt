package c.e.security.repository

import c.e.security.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<Product, Int> {


    fun findProductByownerId(id:Int) :ArrayList<Product>

    fun deleteProductById(productId: Int)

    @Modifying
    @Query(value =  "delete from product   where owner_id = ?1" , nativeQuery = true)
    fun deleteAllProductsByOwnerId(ownerId:Int)


    @Modifying
    @Query(value =  "Update product   SET  quantity=:quantity WHERE  id=:id" , nativeQuery = true)
    fun updateProductQuantityById(@Param("id") productId: Int,@Param("quantity") productQuantity: Int)

}