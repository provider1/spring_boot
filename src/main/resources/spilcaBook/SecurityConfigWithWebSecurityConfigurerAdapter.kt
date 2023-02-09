package c.e.security.spilcaBook


//@Configuration
//@EnableAsync
//class SecurityConfigWithWebSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {
//
//    @Autowired
//    private var authenticationProvider: CustomAuthenticationProvider? = null
//
//
//    //database
//    @Bean
//    fun userDetailsService(dataSource: DataSource?): UserDetailsService? {
//        return JdbcUserDetailsManager(dataSource)
//    }

//    @Bean
//    fun userDetailsService(dataSource: DataSource?): UserDetailsService? {
//        val usersByUsernameQuery = "select username, password  ,'true' from customer where username = ? "
//        val authsByUserQuery = "select username, authority from authorities where username = ?"
//        val userDetailsManager = JdbcUserDetailsManager(dataSource)
//        userDetailsManager.usersByUsernameQuery = usersByUsernameQuery;
//        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
//        return userDetailsManager;
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder? {
//        return NoOpPasswordEncoder.getInstance()
//    }
//
//
//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.authenticationProvider(authenticationProvider!!)
//    }
//
//    @Autowired
//    val failureHandler : CustomAuthenticationFailureHandler? = null
//       @Autowired
//    val successHandler : CustomAuthenticationSuccessHandler? = null
//
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http.formLogin()
//            .defaultSuccessUrl("/hola",true)
//            .successHandler(successHandler)
//            .failureHandler(failureHandler)
//        http.authorizeRequests().anyRequest().authenticated()
//        http.and().httpBasic { it.authenticationEntryPoint(CustomEntryPoint())  }

//    }

//    @Bean
//    fun initializingBean(): InitializingBean? {
//        return InitializingBean {  SecurityContextHolder.setStrategyName(
//                SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)}
//    }

//    override fun configure(auth: AuthenticationManagerBuilder?) {

//
////        ---------------------------------
////        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
////
////        val userDetailsService = InMemoryUserDetailsManager()
////
////        val ahmed =
////            User
////                .withUsername("ahmed")
////                .password(encoder.encode("123456"))
////                .authorities("read")
////                .roles("USER")
////                .accountExpired(false)
////                .accountLocked(false)
////                .disabled(false)
////                .credentialsExpired(false)
////                .build()
////        userDetailsService.createUser(ahmed)
////        auth.userDetailsService(userDetailsService)
////        -----------------------------------
////        auth.inMemoryAuthentication().withUser("ahmed").password(encoder.encode("12345")).roles("USER")
////            .authorities("read")
//
//
//    }
//}


