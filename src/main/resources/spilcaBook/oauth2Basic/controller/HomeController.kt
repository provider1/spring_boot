package c.e.security.spilcaBook.oauth2Basic.controller

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.util.logging.Logger


//@Controller
//class HomeController {
//
//    val logger:Logger = Logger.getLogger(HomeController::class.java.name)
//    @GetMapping("/")
//    fun home(authentication: OAuth2AuthenticationToken): String {
//        logger.info("authentication: $authentication")
//        return "home.html"
//    }
//
//}