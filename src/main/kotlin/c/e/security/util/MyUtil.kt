package c.e.security.util

import c.e.security.model.Customer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCrypt


class MyUtil {

    companion object {
        var logger: Logger = LoggerFactory.getLogger(MyUtil::class.java)
    }


  fun info(info: String) {
        logger.info(info)
    }

    fun userDataIsValid(customer: Customer): Boolean {
        val array = arrayOf(customer.email, customer.customerUsername, customer.pass)
        array.forEach {
            if (it!!.isEmpty())
                return false
        }
        return true
    }

    fun getResponseFailedMessage(customer: Customer): String {
        var message = ""
        if (customer.email!!.isEmpty())
            message += "Response Failed :  Email is required !\n"
        if (customer.pass!!.isEmpty())
            message += "Response Failed :  Password is required !\n"
        if (customer.customerUsername!!.isEmpty())
            message += "Response Failed :  username is required !\n"
        return message
    }

    fun crypt(password:String):String{
        return BCrypt.hashpw(password , BCrypt.gensalt())
    }

    fun comparePassword( password: String ,cryptPassword :String): Boolean {
        return BCrypt.checkpw(password,cryptPassword)
    }

}