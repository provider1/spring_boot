package c.e.security.service

import c.e.security.entity.Customer
import c.e.security.model.Login
import c.e.security.repository.CustomerRepository
import c.e.security.util.MyUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile


@Service
class CustomerService {

    val myUtil by lazy { MyUtil() }

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Transactional(propagation = Propagation.MANDATORY)
    fun registerCustomer(customer: Customer): ResponseEntity<Any> {
        if (!myUtil.customerDetailsAreNotNull(customer))
            return ResponseEntity<Any>(myUtil.getResponseFailedMessage(customer), HttpStatus.BAD_REQUEST)
        customer.pass = myUtil.crypt(customer.pass)

       // myUtil.info(customer.pass!!)
        customerRepository.save(customer)

        return ResponseEntity<Any>(customer, HttpStatus.ACCEPTED)
    }

    @Transactional
    fun login(login: Login): Customer {

        return try {
            val customer = customerRepository.findCustomerByEmail(login.email.lowercase())
            myUtil.info(customer.toString())
            if (myUtil.comparePassword(login.password, customer.pass)) {
                 customer
            } else
                return  Customer()

        } catch (e: EmptyResultDataAccessException) {
         return  Customer()
        }
    }


    fun setProfileImageAndMobileNumber(id: Int, mobile: String, file: MultipartFile): Customer {
        val saveCustomer: Customer = customerRepository.findById(id).orElseThrow()
        saveCustomer.image = file.bytes
        saveCustomer.mobile = mobile
        saveCustomer.profileComplete = true
        customerRepository.save(saveCustomer)
        return saveCustomer
    }

    fun getProfilePicture(id: Int): ByteArray? {
        val customer: Customer = customerRepository.findById(id).orElseThrow()
        return customer.image
    }

    fun getCustomers(): Any {

        return customerRepository.findAll()
    }

    fun deleteCustomer(id: Int): ResponseEntity<Any> {
        val customer: Customer = customerRepository.findById(id).orElseThrow()
        customerRepository.delete(customer)
        return ResponseEntity<Any>(customer.email +" Deleted", HttpStatus.ACCEPTED)
    }

    fun getCustomerIdByEmail(email: String): ResponseEntity<Customer> {
        val customerId =customerRepository.findCustomerByEmail(email.lowercase())

        return ResponseEntity<Customer>(customerId, HttpStatus.ACCEPTED)

    }


}