package c.e.security.spilcaBook.oauth2Customized.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService
import java.util.List
import java.util.Map


//@Configuration
//@EnableAuthorizationServer
//class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {
//
//    @Autowired
//    private val authenticationManager: AuthenticationManager? = null
//
//
//    override fun configure(clients: ClientDetailsServiceConfigurer?) {
//
////        val service = InMemoryClientDetailsService()
////
////        val cd = BaseClientDetails()
////        cd.clientId = "client"
////        cd.clientSecret = "secret"
////        cd.setScope(listOf("read"))
////        cd.setAuthorizedGrantTypes(listOf("password"))
////
////        service.setClientDetailsStore( Map.of("client", cd))
////        clients!!.withClientDetails(service)
//
//        clients!!.inMemory()
//            .withClient("client")
//            .secret("secret")
//            .authorizedGrantTypes("client_credentials")
//            .scopes("all");
//    }
//
//    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
//        endpoints.authenticationManager(authenticationManager)
//    }
//}