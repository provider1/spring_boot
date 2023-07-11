package c.e.security.model

data class AuthenticationRequest(
    var email: String,
    var password: String
)
