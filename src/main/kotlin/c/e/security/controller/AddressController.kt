package c.e.security.controller

import c.e.security.model.Address
import c.e.security.service.AddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RequestMapping("/address")

@RestController
@Suppress("unused")

class AddressController {

    @Autowired
    private lateinit var addressService: AddressService

    @PostMapping("/add")
    fun addProduct(@RequestBody address: Address): ResponseEntity<Address> {
        return ResponseEntity(addressService.addAddress(address), HttpStatus.OK)
    }

    @GetMapping("/get-all")
    fun getAddresses(): ResponseEntity<ArrayList<Address>> {
        return ResponseEntity(addressService.getAddresses(), HttpStatus.OK)
    }

    @GetMapping("/get/{customerId}")
    fun getAddressesByCustomerId(@PathVariable("customerId") customerId: Int): ResponseEntity<ArrayList<Address>> {
        return ResponseEntity(addressService.getAddressByCustomerId(customerId), HttpStatus.OK)
    }


    @Transactional
    @DeleteMapping("/delete-all/customer-id/{customerId}")
    fun deleteAllAddressesByCustomerId(@PathVariable("customerId") customerId: Int): ResponseEntity<String> {
        return ResponseEntity(addressService.deleteAllAddressesByCustomerId(customerId), HttpStatus.OK)
    }

    @Transactional
    @DeleteMapping("/delete/address-id/{addressId}")
    fun deleteAddressByAddressId(@PathVariable("addressId") addressId: Int): ResponseEntity<String> {
        return ResponseEntity(addressService.deleteAddressByAddressId(addressId), HttpStatus.OK)
    }



}