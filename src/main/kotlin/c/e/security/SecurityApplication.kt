package c.e.security

import c.e.security.repository.CustomerRepository
import c.e.security.util.MyUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication
//@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
//@ComponentScan(basePackages = ["c.e.security.service.*"],
//    excludeFilters = [ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = arrayOf("c.e.security.spilcaBook"))]
//)
class SecurityApplication

fun main(args: Array<String>) {
    runApplication<SecurityApplication>(*args)

//	val context = runApplication<SecurityApplication>(*args)
//	val customer = context.getBean(Customer::class.java)
//      lateinit var customerRepository: CustomerRepository
//
//    val customer =  customerRepository.findByEmail()
//    MyUtil().info()

}

