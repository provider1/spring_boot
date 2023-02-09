package c.e.security.controller

import c.e.security.model.CartProduct
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


    @GetMapping("/get-all")
    fun getCartProducts(): ResponseEntity<ArrayList<CartProduct>> {
        val cartProductsResponse = cartProductService.getCartProducts()
        return ResponseEntity(cartProductsResponse, HttpStatus.OK)
    }


    @GetMapping("/get/{customerId}")
    fun getCartProducts(@PathVariable("customerId") customerId: Int): ResponseEntity<ArrayList<CartProduct>> {
        val cartProductsResponse = cartProductService.getCartProducts(customerId)
        return ResponseEntity(cartProductsResponse, HttpStatus.OK)
    }

    @GetMapping("/get-by-product-id/{productId}")
    fun getCartProductsByProductId(@PathVariable("productId")productId:Int ):ArrayList<CartProduct>{
        return cartProductService.getCartProductsByproductId(productId)
    }

    @Transactional
    @DeleteMapping("/delete-all/customer-id/{customerId}")
    fun deleteAllCartProductsByCustomerId(@PathVariable("customerId") customerId: Int): ResponseEntity<String> {
        val cartProductsResponse = cartProductService.deleteAllCartProductsByCustomerId(customerId)
        return ResponseEntity(cartProductsResponse, HttpStatus.OK)
    }




    @Transactional
    @DeleteMapping("/delete/cart-product-id/{cartProductId}")
    fun deleteCartProductByCartProductId(@PathVariable("cartProductId") cartProductId: Int): ResponseEntity<String> {
        val cartProductsResponse = cartProductService.deleteCartProductsByCartProductId(cartProductId)
        return ResponseEntity(cartProductsResponse, HttpStatus.OK)
    }


}