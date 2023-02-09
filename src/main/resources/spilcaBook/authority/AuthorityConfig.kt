package c.e.security.spilcaBook.authority

//
////@Configuration
//class AuthorityConfig : WebSecurityConfigurerAdapter() {
//
//
//    @Bean
//    public override fun userDetailsService(): UserDetailsService {
//        val userDetailsManager = InMemoryUserDetailsManager()
//        val user1 = User.withUsername("ahmed")
//            .password("12345")
//            .authorities("read")
////            .authorities("ROLE_MANAGER")
////            .roles(admin)
//            .build()
//        val user2 = User.withUsername("sarah")
//            .password("12345")
//            .authorities("read")
////            .authorities("ROLE_ADMIN")
////            .roles(manager)
//            .build()
//        userDetailsManager.createUser(user1)
//        userDetailsManager.createUser(user2)
//        return userDetailsManager
//    }
//
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return NoOpPasswordEncoder.getInstance()
//    }
//
////
////    @Autowired
////    val staticKeyAuthenticationFilter: StaticKeyAuthenticationFilter? = null
//
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//
////        http.httpBasic().and().formLogin()
////       == == == == == == == == == ==  AUTHORITIES == == == == == == == == == == == == == == == == == == == == ==
////        http.authorizeRequests().anyRequest().hasAnyAuthority(delete , update)
////        http.authorizeRequests().anyRequest().hasAuthority(read);
////        http.authorizeRequests().anyRequest().access("hasAuthority('$update')")
////        http.authorizeRequests().anyRequest().access("hasAuthority('$read') and !hasAuthority('$delete')")
//
////       == == == == == == == == == ==  ROLES == == == == == == == == == == == == == == == == == == == == ==
////        http.authorizeRequests().anyRequest().hasRole(admin)
////------
////        http.authorizeRequests()
////            .mvcMatchers("/hello").hasRole(admin)
////            .mvcMatchers("/ciao").hasRole(manager)
////------
////        http.authorizeRequests()
////            .mvcMatchers(HttpMethod.GET, "/a")
////            .authenticated()
////            .mvcMatchers(HttpMethod.POST, "/a")
////            .permitAll()
////            .anyRequest()
////            .denyAll();
////------
////        http.authorizeRequests()
////            .mvcMatchers(HttpMethod.GET, "/a/b/**" )//The /a/b/** expression refers to all paths prefixed with /a/b
////            .authenticated()
////            .mvcMatchers( "/a")
////            .permitAll()
////            .anyRequest()
////            .denyAll()
////------
////
////            http.authorizeRequests()
////                .mvcMatchers("/hello/{name:^[0-9]*$}").permitAll()
////                .anyRequest().denyAll()
////------
////
////
////        http.authorizeRequests()
////            .antMatchers("/life").permitAll()
////            .antMatchers("/life/").permitAll()
////            .anyRequest().denyAll()
////
////------
////
////        http
////            .addFilterBefore(RequestValidationFilter(), BasicAuthenticationFilter::class.java)
////            .authorizeRequests()
////            .anyRequest().denyAll()
////            .and()
////            .addFilterAfter(AuthenticationLoggingFilter() , BasicAuthenticationFilter::class.java)
////------
////
////
////        http.addFilterAt(staticKeyAuthenticationFilter , BasicAuthenticationFilter::class.java)
////            .authorizeRequests().anyRequest().permitAll()
////------
////
////        http
////            .authorizeRequests().anyRequest().permitAll().and()
////            .addFilterAfter(AuthenticationLoggingFilterOncePerRequestFilter(), BasicAuthenticationFilter::class.java)
////
//
////------
////        http.httpBasic().and()
////            .authorizeRequests()
////            .anyRequest().permitAll().and()
////           .addFilterAfter(CsrfTokenLogger(), CsrfFilter::class.java)
//
//        http.csrf().disable()
//        http
//            .authorizeRequests()
//            .anyRequest().permitAll()
//
//
//
//    }
//}

