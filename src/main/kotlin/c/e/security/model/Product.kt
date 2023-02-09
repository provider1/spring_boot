package c.e.security.model

import javax.persistence.*


@Entity
@Table(name = "product")
data class Product(
//    @Column(name ="owner_id")
    var ownerId: Int? = null,
    var ownerUsername: String = "",
    var title: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var quantity: Int? =null,
    @Lob
    var image: ByteArray?= null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
)






