package c.e.security.controller

import c.e.security.entity.Order
import c.e.security.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional


@RestController
@RequestMapping("/order")
@Suppress("unused")
class OrderController {

    @Autowired
    private lateinit var orderService: OrderService

    @Transactional
    @PostMapping("/add")
    fun addOrder(@RequestBody order: Order): Order {
        return orderService.addOrder(order)
    }



    @Transactional
    @GetMapping("/get-all")
    fun getAllOrders(): List<Order> {
        return orderService.getAllOrders()
    }

    @Transactional
    @GetMapping("/get-by-customer-id/{customerId}")
    fun getOrdersByCustomerId(@PathVariable customerId: Int): List<Order> {
        return orderService.getOrdersByCustomerId(customerId)
    }

    @Transactional
    @DeleteMapping("/delete/{orderId}")
    fun deleteOrderById(@PathVariable orderId: Int):String {
        orderService.deleteOrderById(orderId)
        return "order is Deleted "
    }

    @Transactional
    @DeleteMapping("/delete-all/customer-id/{customerId}")
    fun deleteAllOrdersByCustomerId(@PathVariable customerId: Int) {
        orderService.deleteAllOrdersByCustomerId(customerId)
    }


}