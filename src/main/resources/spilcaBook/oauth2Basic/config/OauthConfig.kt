package c.e.security.spilcaBook.oauth2Basic.config

//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
//import org.springframework.security.oauth2.client.registration.ClientRegistration
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
//
//
////@Configuration
//class OauthConfig : WebSecurityConfigurerAdapter() {
//
//
//    @Bean
//    fun clientRepository(): ClientRegistrationRepository {
//        val c = clientRegistration()
//        return InMemoryClientRegistrationRepository(c)
//    }
//
//    private fun clientRegistration(): ClientRegistration {
//        return CommonOAuth2Provider.GITHUB.getBuilder("github")
//            .clientId("788fb37da7b724126be1")
//            .clientSecret("6b1f99d5a0118251bd945863feeccd60d9bd8eb3")
//            .build()
//
//    }
//
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http.oauth2Login()
//            .and()
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
//
////        http.oauth2Login { it.clientRegistrationRepository(clientRepository())}
//    }
//
//}
