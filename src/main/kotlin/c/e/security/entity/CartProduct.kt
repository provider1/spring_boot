package c.e.security.entity

import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "cart_product")
data class CartProduct(
    var customerId: Int? = null,
    var productId: Int? = null,
    @Column(length = 20)
    var title: String? = null,
    @Column(length = 20)
    var price: String? = null,
    @Lob
    @Column( columnDefinition = "MEDIUMBLOB")
    var image: ByteArray? = null,
    @Column(length = 20)
    var cartQuantity: String? = null,
    @Column(length = 30)
    var stockQuantity: String? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    ): Serializable