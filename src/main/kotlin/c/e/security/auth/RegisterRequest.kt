package c.e.security.auth

data class RegisterRequest(
     val username:String? = null,
     val email: String? = null,
     val password: String? = null
)
