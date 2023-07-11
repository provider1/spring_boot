package c.e.security.service

import c.e.security.entity.Customer
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys.hmacShaKeyFor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*


@Service
class JwtService {
//    val key1: ByteArray = KeyGenerators.secureRandom(40).generateKey()
    val aud = "audience"
    val iss = "abraj store"

    val map1 = HashMap<String, Any>().apply{
        put("typ", "JWT")
        put("from", "kwt")
        put("alg", "HS256")
        put("alg", "HS256")
    }



    fun extractEmail(token: String): String {

        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun generateToken(userDetails: Customer): String? {
        val claims: HashMap<String, Any> = HashMap()
        claims["username"] = userDetails.customerUsername.toString()
        claims["role"] = userDetails.role.toString()
        return generateToken(claims, userDetails)
    }

    fun generateToken(
        extraClaims: HashMap<String, Any>,
        userDetails: UserDetails
    ): String {

        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setHeader(map1)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() +( 1000 * 60* 60 * 24) ))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun extractAllClaims(token: String): Claims {
        val jwt = Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
//            .requireIssuer(iss)
            .build()
            .parseClaimsJws(token)
        return jwt.body
    }

    private fun extractAllHeaders(token: String): Map<String, Any> {
        val jwt = Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)

        return jwt.header
    }


    private fun getSignInKey(): Key? {
        val secret: String =
            "5166546A576E5A7234753778214125442A472D4A614E" +
                    "645267556B58703273357638792F423F4528482B4D625065536" +
                    "8566D597133743677397A24432646294A404E635266546A576E5A7234753778214125442A" +
                    "472D4B6150645367566B58703273357638792F423F4528482B4D6251655468576D5A7133743677397A244" +
                    "32646294A404E635266556A586E327235753778214125442A472D4" +
                    "B6150645367566B597033733676397924423" +
                    "F4528482B4D6251655468576D5A713474"
        val keyBytes = Decoders.BASE64.decode(secret)

        return hmacShaKeyFor(keyBytes)
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractEmail(token)
        val isUsernameMatched = username == userDetails.username
        val isTokenIssuerMatched = extractClaim(token, Claims::getIssuer) == iss
        val isTokenAudienceMatched = extractClaim(token, Claims::getAudience) == aud
        val isTokenHeadersMatched = extractAllHeaders(token) == map1
        return    isUsernameMatched && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

}
