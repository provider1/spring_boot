package c.e.security.service

import c.e.security.entity.Address
import c.e.security.repository.AddressRepository
import c.e.security.util.MyUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class AddressService {

    val myUtil by lazy { MyUtil() }

    @Autowired
    private lateinit var addressRepository: AddressRepository

    fun addAddress(address: Address): Address {
        return addressRepository.save(address)
    }


    fun getAddresses(): ArrayList<Address> {
        return addressRepository.findAll() as ArrayList<Address>
    }


    fun getAddressByCustomerId(customerId: Int): ArrayList<Address> {
        return addressRepository.findAddressBycustomerId(customerId)
    }
    @Transactional
    fun deleteAllAddressesByCustomerId(customerId: Int) :String{
        addressRepository.deleteAllAddressesCustomerId(customerId)
        return "Address is Deleted"
    }

    fun deleteAddressByAddressId(addressId: Int) :String{
        addressRepository.deleteById(addressId)
        return "Address is Deleted"
    }


}