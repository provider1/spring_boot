package c.e.security.spilcaBook.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails



//class User (private val  username :String , private val password:String , private val authority:String):UserDetails{
//    override fun getAuthorities(): List< GrantedAuthority> {
//
//        return listOf(SimpleGrantedAuthority(authority))
//    }
//
//    override fun getPassword(): String {
//        return password
//    }
//
//    override fun getUsername(): String {
//        return username
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        return true
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isEnabled(): Boolean {
//        return true
//    }
//
//
//}