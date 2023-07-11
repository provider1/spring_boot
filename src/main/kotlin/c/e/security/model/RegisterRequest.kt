package c.e.security.model

data class RegisterRequest(
     val username:String? = null,
     val email: String? = null,
     val password: String? = null
)
