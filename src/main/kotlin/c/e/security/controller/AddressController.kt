package c.e.security.controller

import c.e.security.entity.Address
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

    //delete this on production
      @PostMapping("/add-test")
    fun addProductTest(@RequestBody address: Address):  Address  {
        return  addressService.addAddress(address)
    }

//    @ResponseStatus()
    @GetMapping("/get-all")
    fun getAddresses(): ArrayList<Address>{
        return  addressService.getAddresses()
    }
    @GetMapping("/get/{customerId}")
    fun getAddressesByCustomerId(@PathVariable("customerId") customerId: Int): ArrayList<Address> {
        return addressService.getAddressByCustomerId(customerId)
    }
    @Transactional
    @DeleteMapping("/delete-all/customer-id/{customerId}")
    fun deleteAllAddressesByCustomerId(@PathVariable("customerId") customerId: Int): ResponseEntity<String> {
        return ResponseEntity(addressService.deleteAllAddressesByCustomerId(customerId), HttpStatus.OK)
    }

    @Transactional
    @DeleteMapping("/delete/address-id/{addressId}")
    fun deleteAddressByAddressId(@PathVariable("addressId") addressId: Int):  String  {
        return addressService.deleteAddressByAddressId(addressId)
    }



}