package c.e.security.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "customer" ,uniqueConstraints = [UniqueConstraint(name = "email", columnNames = ["email"])])
data class Customer(
    @Column(length = 30)
    var customerUsername: String? =null,
    @Column(name = "email" , nullable = false , length = 30)
    var email: String? =null,
    @Column(length = 30)
    var pass: String? = null,
    @Column(length = 20)
    var mobile: String? = null,
    @Lob
    var image: ByteArray? = null,
    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var createdAt: Date = Date(),
    @Temporal(TemporalType.DATE)
    var updatedAt: Date = Date(),
    var profileComplete:Boolean = false,
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    var role: Role?=null,
    var token:String? = null ,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
) : UserDetails {

    override fun getAuthorities()= listOf(GrantedAuthority { role!!.name }).toMutableList()
    override fun getPassword(): String = pass!!
    override fun getUsername(): String = email!!
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean =true
    override fun isEnabled(): Boolean =true
}
enum class Role {
    USER, ADMIN
}


