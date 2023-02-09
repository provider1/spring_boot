package c.e.security.spilcaBook.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.security.concurrent.DelegatingSecurityContextCallable
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import java.util.concurrent.Callable
import java.util.concurrent.Executors


//@RestController
//class HelloController {
//    @Value("\${welcome.message}")
//    private val welcomeMessage: String? = null
//
//
//    //a technique to copy the details from the security context by making use of the task itself
//    @GetMapping("/welcome")
//    @Throws(Exception::class)
//    fun welcome(): String? {
//        val task = Callable {
//            val context =
//                SecurityContextHolder.getContext()
//            context.authentication.isAuthenticated
//        }
//        val e = Executors.newCachedThreadPool()
//        return try {
//            val contextTask = DelegatingSecurityContextCallable(task)
//            "welcome, " + e.submit(contextTask).get() + "!"
//        } finally {
//            e.shutdown()
//        }
//    }
//
//    //this is to manage propagation from the thread pool instead of from the task itself.
//    @GetMapping("/hola")
//    @Async
//    @Throws(java.lang.Exception::class)
//    fun hola(): String? {
//        val task = Callable {
//            val context =
//                SecurityContextHolder.getContext()
//            context.authentication.name
//        }
//        var e = Executors.newCachedThreadPool()
//        e = DelegatingSecurityContextExecutorService(e)
//        return try {
//            "Hola, " + e.submit(task).get() + "!"
//        } finally {
//            e.shutdown()
//        }
//    }
//
//    @GetMapping("/hello")
//    fun hello(authentication:Authentication): String {
////        val securityContext = SecurityContextHolder .getContext()
////        val authentication = securityContext.authentication
//
//        val helloMessage = """ Hello ${authentication.name}
//            |Details: ${authentication.details}
//            |Authorities: ${authentication.authorities}
//            |isAuthenticated: ${authentication.isAuthenticated}
//            |Password: ${authentication.credentials}
//        """.trimMargin()
//        val name = authentication.name
//
//        return "s"
//    }
//
//
//
//}