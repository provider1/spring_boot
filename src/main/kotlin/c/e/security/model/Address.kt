package c.e.security.model

import javax.persistence.*

@Entity
@Table(name ="address")
data class Address (
    var customerId: Int? = null,
    @Column(length = 40)
    var name: String = "",
    @Column(length = 20)
    var phoneNumber: String = "",
    @Column(length = 40)
    var location: String = "",
    @Column(length = 15)
    var zipCode: String = "",
    @Column(length = 50)
    var note: String = "",
    @Column(length = 40)
    var addressType: String = "",
    @Column(length = 20)
    var otherAddress: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
)