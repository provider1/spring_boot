package c.e.security.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name = "customer")
data class Customer(
        @Column(length = 20)
        var customerUsername: String = "",
        @Column(name = "email", nullable = false, length = 30, unique = true)
        var email: String = "",
        @Column(length = 80)
        var pass: String = "",
        @Column(length = 30)
        var mobile: String? = "",
        @Lob
        var image: ByteArray? = null,
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    var createdAt: Date = Date(),
//    @Temporal(TemporalType.DATE)
//    var updatedAt: Date = Date(),
        var profileComplete: Boolean = false,
        var role: Role? = null,
        var token: String? = null,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return listOf(GrantedAuthority { role!!.name }).toMutableList()
    }


    //    override fun getAuthorities()= listOf(GrantedAuthority { role!!.name }).toMutableList()
    override fun getPassword(): String = pass!!
    override fun getUsername(): String = email!!
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}


enum class Role {
    USER, ADMIN
}


