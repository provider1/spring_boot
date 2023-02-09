package c.e.security.model

import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "cart_product")
data class CartProduct(
    var customerId: Int? = null,
    var productId: Int? = null,
    var title: String = "",
    var price: String = "",
    @Lob
    @Column( columnDefinition = "MEDIUMBLOB")
    var image: ByteArray? = null,
    var cartQuantity: String = "",
    var stockQuantity: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    ): Serializable