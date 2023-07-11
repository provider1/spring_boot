package c.e.security.controller

import c.e.security.entity.CartProduct
import c.e.security.service.CartProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RequestMapping("/cart-product")
@RestController
@Suppress("unused")
class CartProductController {


    @Autowired
    private lateinit var cartProductService: CartProductService

    @PostMapping("/add")
    fun addCartProduct(@RequestBody cartProduct: CartProduct): ResponseEntity<CartProduct> {
        val cartProductResponse = cartProductService.addCartProduct(cartProduct)
        return ResponseEntity(cartProductResponse, HttpStatus.OK)
    }

    @PostMapping("/add-test")
    fun addCartProductTest(@RequestBody cartProduct: CartProduct): CartProduct {
        return cartProductService.addCartProduct(cartProduct)
    }
    @GetMapping("/get-all")
    fun getCartProductsByCustomerId(): ArrayList<CartProduct> {
        return cartProductService.getCartProducts()
    }

    @GetMapping("/get-by-customerId/{customerId}")
    fun getCartProductsByCustomerId(@PathVariable("customerId") customerId: Int): List<CartProduct> {
        return cartProductService.getCartProducts(customerId)
    }

    @GetMapping("/get-by-product-id/{productId}")
    fun getCartProductsByProductId(@PathVariable("productId")productId:Int ):List<CartProduct>{
        return cartProductService.getCartProductsByproductId(productId)
    }
    @Transactional
    @DeleteMapping("/delete-all/customer-id/{customerId}")
    fun deleteAllCartProductsByCustomerId(@PathVariable("customerId") customerId: Int): String {
        return  cartProductService.deleteAllCartProductsByCustomerId(customerId)
    }
    @Transactional
    @DeleteMapping("/delete/cart-product-id/{cartProductId}")
    fun deleteCartProductByCartProductId(@PathVariable("cartProductId") cartProductId: Int): String {
        return cartProductService.deleteCartProductsByCartProductId(cartProductId)
    }
}