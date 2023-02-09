package c.e.security.model

import javax.persistence.*


@Entity
@Table(name = "orders")
data class Order(
    var customerId: Int? = null,
    @Lob
    var items: ArrayList<CartProduct> = ArrayList(),
    @OneToOne(cascade = arrayOf (CascadeType.MERGE , CascadeType.REMOVE   ) , orphanRemoval=true)
    var address: Address = Address() ,
    @Column(length = 20)
    var title: String = "",
    @Lob
    var image: ByteArray? = null,
    @Column(length = 20)
    var subTotalAmount: String = "",
    var shippingCharge: Int = 0,
    @Column(length = 10)
    var totalAmount: String = "",
    var date: Long = 0,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

)