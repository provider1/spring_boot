package c.e.security.controller

import c.e.security.model.Product
import c.e.security.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController

@RequestMapping("/products")
@Suppress("unused")
class ProductController {


    @Autowired
    private lateinit var productService: ProductService

    @Transactional
    @PostMapping("/add")
    fun addProduct(@RequestBody product: Product): ResponseEntity<Product> {

      return ResponseEntity(productService.addProduct(product) , HttpStatus.OK)

    }

    @PostMapping(value = ["/upload-image/{id}"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadProductImage(
        @PathVariable("id") id: Int,
        @RequestPart(value = "file") file: MultipartFile
    ): ResponseEntity<Product> {

        val product = productService.uploadProductImage(id, file)
        return ResponseEntity(product, HttpStatus.ACCEPTED)

    }

    @GetMapping("/get-all")
    fun getProducts(): ArrayList<Product> {

        return productService.getProducts()
    }

    @GetMapping("/get/{customerId}")
    fun getProductByCustomerId(@PathVariable("customerId") id:Int ): ResponseEntity<ArrayList<Product>> {
        return ResponseEntity(productService.getProductByCustomerId(id), HttpStatus.OK)
    }

    @Transactional
    @DeleteMapping("/delete-all/owner-id/{ownerId}")
    fun deleteAllProductsByOwnerId(@PathVariable("ownerId") ownerId: Int): ResponseEntity<String> {
        productService.deleteAllProductsByOwnerId(ownerId)
        return ResponseEntity("product deleted", HttpStatus.OK)
    }



   @Transactional
    @DeleteMapping("/delete/product-id/{productId}")
    fun deleteProductById(@PathVariable("productId") productId: Int): ResponseEntity<String> {
        productService.deleteProductById(productId)
        return ResponseEntity("product deleted", HttpStatus.OK)
    }



@Transactional
    @PatchMapping("/update/product-id/{productId}/{productQuantity}")
    fun updateProductQuantityById(@PathVariable("productId") productId: Int,@PathVariable("productQuantity") productQuantity: Int) {
        productService.updateProductQuantityById(productId , productQuantity)

    }








}