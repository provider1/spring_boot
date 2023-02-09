package c.e.security.service

import c.e.security.model.Product
import c.e.security.repository.ProductRepository
import c.e.security.util.MyUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.transaction.Transactional


@Service
class ProductService {

    val myUtil by lazy { MyUtil() }

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun addProduct(product: Product): Product {
        val response = productRepository.save(product)
        return response
    }


    fun uploadProductImage(id: Int, file: MultipartFile): Product {
        myUtil.info(id.toString())
        val product = productRepository.findById(id).orElse(null)
        product.image = file.bytes
        myUtil.info(product.image.toString())
        productRepository.save(product)

        return product
    }

    fun getProducts(): ArrayList<Product> {
        val arr = productRepository.findAll() as ArrayList<Product>

        return arr
    }

    fun getProductById(id: Int): Product {

        return productRepository.findById(id).get()
    }

    fun getProductByCustomerId(id: Int): ArrayList<Product> {
        return productRepository.findProductByownerId(id)
    }

    @Transactional
    fun deleteAllProductsByOwnerId(ownerId: Int) {
        productRepository.deleteAllProductsByOwnerId(ownerId)
    }

    fun deleteProductById(productId: Int) {
        productRepository.deleteProductById(productId)
    }

    @Transactional
    fun updateProductQuantityById(productId: Int, productQuantity: Int) {

        val product = getProductById(productId)
        product.quantity =  Math.abs( product.quantity!! - productQuantity)
        productRepository.updateProductQuantityById(productId, product.quantity!!)

    }


}

