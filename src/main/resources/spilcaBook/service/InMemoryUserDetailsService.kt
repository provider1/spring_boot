package c.e.security.spilcaBook.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException


//class InMemoryUserDetailsService(private val users:List<UserDetails>):UserDetailsService{
//
//    override fun loadUserByUsername(username: String?): UserDetails {
//       return users.firstOrNull { it.getUsername() == username } ?: throw UsernameNotFoundException("Username not found")
//    }
//
//
//}