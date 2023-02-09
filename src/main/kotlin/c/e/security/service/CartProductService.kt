package c.e.security.service

import c.e.security.model.CartProduct
import c.e.security.repository.CartProductRepository
import c.e.security.util.MyUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap


@Service
class CartProductService {


    val myUtil by lazy { MyUtil() }

    @Autowired
    private lateinit var cartProductRepository: CartProductRepository


    fun addCartProduct(cartProduct: CartProduct):CartProduct{
        return cartProductRepository.save(cartProduct)
    }

   fun getCartProducts():ArrayList<CartProduct>{
        return cartProductRepository.findAll() as ArrayList<CartProduct>
    }


   fun getCartProducts(customerId :Int):ArrayList<CartProduct>{
        return cartProductRepository.findCartProductBycustomerId(customerId)
    }


    fun deleteAllCartProductsByCustomerId(customerId :Int):String {
        cartProductRepository.deleteAllCartProductsBycustomerId(customerId)
        return "cart products is Deleted"
    }

    fun deleteCartProductsByCartProductId(cartProductId :Int):String {
        cartProductRepository.deleteById(cartProductId)
        return "cart product is Deleted"
    }

    fun getCartProductsByproductId(productId:Int):ArrayList<CartProduct>{
        return cartProductRepository.findCartProductByproductId(productId)
    }




}