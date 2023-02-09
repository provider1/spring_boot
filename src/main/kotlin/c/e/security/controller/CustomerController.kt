package c.e.security.controller

import c.e.security.model.Customer
import c.e.security.model.Login
import c.e.security.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/customers")
@Suppress("unused")
class CustomerController() {


    @Autowired
    private lateinit var customerService: CustomerService




    @GetMapping("/get-all")
    fun getCustomers(): Any {
        return customerService.getCustomers()
    }

    @Transactional
    @PostMapping("/login")
    fun loginCustomer(@RequestBody login: Login): ResponseEntity<Customer> {
        return customerService.login(login)
    }


    @PostMapping(value = ["/upload/{id}/{mobile}"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun setProfileImageAndMobilePhone(
        @PathVariable("id") id: Int,
        @PathVariable("mobile") mobile: String,
        @RequestPart(value = "file") file: MultipartFile
    ): ResponseEntity<Customer> {

        val customer = customerService.setProfileImageAndMobileNumber(id, mobile, file)
        return ResponseEntity(customer, HttpStatus.ACCEPTED)

    }


    @PostMapping("/download/{userId}")
    fun getProfilePicture(@PathVariable("userId") id: Int): ResponseEntity<Any> {

        return try {
            val image: ByteArray? = customerService.getProfilePicture(id)

            ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${System.currentTimeMillis()}\"")
                .body(image)

        } catch (error: NoSuchElementException) {
            ResponseEntity
                .notFound()
                .build()
        }

    }


    @Transactional
    @DeleteMapping("/delete/{id}")
    fun deleteCustomer(@PathVariable("id") id: Int): ResponseEntity<Any> {
        return customerService.deleteCustomer(id)
    }

    }


